package com.thl.spring.redis;

import com.thl.spring.redis.model.UserCounter;
import com.thl.spring.redis.service.UserCounterService;
import com.thl.spring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class SessionFilter extends OncePerRequestFilter {

    private static final int LIMIT = 50;
    private final UserCounterService userCounterService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        if (authorizationHeader != null) {
            authenticate(authorizationHeader);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String authorizationHeader) {
        String decodedAuth = new String(Base64.getDecoder().decode(authorizationHeader.replaceAll("Basic ", "")));
        String[] userDetails = decodedAuth.split(":");
        if (userDetails.length == 2) {
            userService.findByUsername(userDetails[0]).ifPresent(userEntity -> {
                if (bCryptPasswordEncoder.matches(userDetails[1], userEntity.getPassword()) &&
                        isUserCounterNotExceeded(userDetails[0])) {

                    User user = userEntity.toUser();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user,
                            user.getPassword(), user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            });
        }
    }

    private boolean isUserCounterNotExceeded(String username) {
        Optional<UserCounter> redisUserCounterOptional = userCounterService.find(username);
        if (redisUserCounterOptional.isEmpty() || isTimePassed(Instant
                .ofEpochMilli(redisUserCounterOptional.get().getDate()))) {
            userCounterService.set(username, 1);
        } else {
            int userCounter = redisUserCounterOptional.get().getCounter();
            if (userCounter < LIMIT) {
                userCounter++;
                userCounterService.set(username, userCounter);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isTimePassed(Instant userTime) {
        return ZonedDateTime.ofInstant(userTime, ZoneOffset.UTC).isAfter(ZonedDateTime.now(ZoneOffset.UTC).plusHours(24));
    }
}

