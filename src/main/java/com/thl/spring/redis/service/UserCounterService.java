package com.thl.spring.redis.service;

import com.thl.spring.redis.model.UserCounter;

import java.util.Optional;

public interface UserCounterService {

    void save(String username, UserCounter userCounter);

    Optional<UserCounter> find(String username);

    void set(String username, int counter);
}
