package com.thl.spring.service;

import com.thl.spring.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService{
    Optional<User> findByUsername(String login);

    void saveUser(User user);

    public UserDetails loadUserByUsername(String username);
}
