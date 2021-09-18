package com.thl.spring.anti_spam_system;

import com.thl.spring.anti_spam_system.service.AntiSpamService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AntiSpam {

    private final AntiSpamService antiSpamService;
    private final int maxDayActivity = 5;

    //Generates counter for user activity per day
    public void generateCounter(String username) {
        antiSpamService.save(username, 0);
    }

    public void userMadeActivity(SecurityContext securityContext) {
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        String username = user.getUsername();
        antiSpamService.find(username).ifPresentOrElse(integer -> {
            if (integer < maxDayActivity) {
                antiSpamService.save(username, integer + 1);
            } else {
                bUser(securityContext);
            }

        }, () ->

        {
            throw new UsernameNotFoundException("UserNameNotFound");
        });
    }

    private void bUser(SecurityContext context) {
        context.getAuthentication().setAuthenticated(false);
    }
}
