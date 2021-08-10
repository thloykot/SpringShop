package com.thl.spring.service;

import com.thl.spring.model.User;

import java.util.Optional;

public interface UserService{
    Optional<User> findByUsername(String username);

    void saveUser(User user);

}
