package com.thl.spring.redis.service;

import com.thl.spring.redis.model.RedisUserCounter;

import java.util.Optional;

public interface RedisService {

    void save(String username, RedisUserCounter user);

    Optional<RedisUserCounter> find(String username);

    void setUserCounter(String username, int counter);
}
