package com.thl.spring.anti_spam_system;

import com.thl.spring.anti_spam_system.service.AntiSpamService;
import com.thl.spring.model.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@AllArgsConstructor
@Component
@Slf4j
public class AntiSpamSystem {

    private final AntiSpamService antiSpamService;
    private final int maxDayActivity = 50;
    private final Timer timer = new Timer();
    private final long delay = 86400000;/*  1000 * 60 * 60 * 24 = 86400000 is 24 hours period */


    public void generateCounter(String username) {
        antiSpamService.save(username, 0);
    }

    public void userMadeActivity(SecurityContext securityContext) {
        String username = securityContext.getAuthentication().getName();
        antiSpamService.find(username).ifPresentOrElse(integer -> {
            if (integer < maxDayActivity) {
                antiSpamService.save(username, integer + 1);
            } else {
                antiSpam(securityContext);
            }
        }, () ->
        {
            throw new UsernameNotFoundException("UserNameNotFound");
        });
    }

    private void antiSpam(SecurityContext context) {
        lockUserSession(context);
        allowUserSessionAfterTime(context, delay);
    }

    private void lockUserSession(SecurityContext context) {
        Authentication authentication = context.getAuthentication();
        log.info("user: {} has been banned", authentication.getName());
        context.setAuthentication(new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),
                List.of(new SimpleGrantedAuthority(Role.BANNED.name()))));
    }


    private void allowUserSessionAfterTime(SecurityContext context, long delay) {
        TimerTask timerTask = makeActivityAfterTime(context);

        timer.schedule(timerTask, delay);
    }

    private TimerTask makeActivityAfterTime(SecurityContext context) {
        return new TimerTask() {
            @Override
            public void run() {
                Authentication authentication = context.getAuthentication();
                String name = authentication.getName();
                log.info("user:{} has been unbanned", name);

                context.setAuthentication(new UsernamePasswordAuthenticationToken(
                        authentication.getPrincipal(),
                        authentication.getCredentials(),
                        List.of(new SimpleGrantedAuthority(Role.USER.name()))));

                generateCounter(name);
            }
        };
    }
}
