package com.thl.spring.service;

import com.thl.spring.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    User save(User user) throws Exception;

    User get(String username);

    boolean isExists(String username);

}
