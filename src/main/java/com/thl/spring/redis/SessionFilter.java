package com.thl.spring.redis;

import com.thl.spring.redis.model.RedisUser;
import com.thl.spring.redis.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class SessionFilter implements Filter {

    private final RedisService redisService;
    private final int limit = 50;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) request;
        Optional<Principal> principal = Optional.ofNullable(req.getUserPrincipal());

        principal.ifPresentOrElse(user ->
                redisService.find(user.getName()).ifPresentOrElse(redisUser -> {
                    try {
                        if (new Date().after(redisService.getUnlockDate(user.getName()))) {
                            setCounterToOne(user.getName());
                            passUser(request, response, chain);
                        } else {
                            int counter = redisUser.getCounter();
                            if (counter <= limit) {
                                incrementCounter(user.getName(), counter);
                                passUser(request, response, chain);
                            }
                        }
                    } catch (ServletException | IOException e) {
                        e.printStackTrace();
                    }
                }, () -> setCounterToOne(user.getName())), () -> {
            try {
                passUser(request, response, chain);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        });
    }

    private void passUser(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }

    private void setCounterToOne(String username) {
        log.info("number of {}'s requests has been restarted", username);
        redisService.save(username, new RedisUser(1, new Date()));
    }

    private void incrementCounter(String username, int counter) {
        int newCounter = counter + 1;
        log.info("user {} has made request № {}", username, newCounter);
        redisService.save(username, new RedisUser(newCounter, new Date()));
    }
}
