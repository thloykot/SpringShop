package com.thl.spring.service.impl;

import com.thl.spring.model.UserEntity;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LoginUserServise implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username).map(UserEntity::toUser).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
