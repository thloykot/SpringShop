package com.thl.spring.redis;

import com.thl.spring.model.UserEntity;
import com.thl.spring.redis.model.RedisUserCounter;
import com.thl.spring.redis.service.RedisService;
import com.thl.spring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class SessionFilter extends OncePerRequestFilter {

    private static final int LIMIT = 50;
    private final RedisService redisService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ZoneId zoneId;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        if (authorizationHeader != null) {
            authorize(authorizationHeader);
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().equals("/register");
    }

    private void authorize(String authorizationHeader) {
        String decodedAuth = new String(Base64.getDecoder().decode(authorizationHeader.replaceAll("Basic ", "")));
        String[] userDetails = decodedAuth.split(":");
        if (userDetails.length == 2) {
            userService.findByUsername(userDetails[0]).ifPresent(userEntity -> {
                if (bCryptPasswordEncoder.matches(userDetails[1], userEntity.getPassword()) &&
                        isUserCounterNotExceeded(userDetails[0])) {
                    authenticate(userEntity);
                }
            });
        }
    }

    private void authenticate(UserEntity userEntity) {
        User user = userEntity.toUser();
        String credentials = user.getPassword();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, credentials, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean isUserCounterNotExceeded(String username) {
        return redisService.find(username).map(redisUserCounter -> {
            if (isTimePassed(redisUserCounter.getDate())) {
                redisService.setUserCounter(username,1);
            }
            else {
                int userCounter = redisUserCounter.getCounter();
                if(userCounter < LIMIT){
                    userCounter++;
                    redisService.setUserCounter(username,userCounter);
                }
                else {
                    return false;
                }
            }
            return true;
        }).orElseGet(() -> {
            redisService.setUserCounter(username, 1);
            return true;
        });
    }

    public boolean isTimePassed(Timestamp userTime) {
        return ZonedDateTime.now(zoneId).plusHours(24).isAfter(ZonedDateTime.of(userTime.toLocalDateTime(), zoneId));
    }
}

