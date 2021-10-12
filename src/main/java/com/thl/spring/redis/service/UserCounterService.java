package com.thl.spring.redis.service;

import com.thl.spring.redis.model.RedisUserCounter;

import java.util.Optional;

public interface UserCounterService {

    void save(String username, RedisUserCounter user);

    Optional<RedisUserCounter> find(String username);

    void set(String username, int counter);
}
