package com.thl.spring.redis;

import com.thl.spring.redis.model.RedisUserCounter;
import com.thl.spring.redis.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class SessionFilter extends OncePerRequestFilter {

    private static final int LIMIT = 8;
    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        isUserAuthenticated(request);
        /*String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<RedisUserCounter> OptionalRedisUserCounter = redisService.find(username);
        if (OptionalRedisUserCounter.isPresent()) {
            RedisUserCounter redisUserCounter = OptionalRedisUserCounter.get();
            if (redisService.isTimePassed(username)) {
                redisService.setUserCounter(username, 1);
            } else {
                int userCounter = redisUserCounter.getCounter();
                if (userCounter < LIMIT) {
                    userCounter++;
                    redisService.setUserCounter(username, userCounter);
                } else {
                    SecurityContextHolder.clearContext();
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }
        } else {
            redisService.setUserCounter(username, 1);

        }
        */
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().equals("/register");
    }

    private void isUserAuthenticated(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String UserInfo = principal.getName() + ":" + principal.getClass();
    }
}
