package com.thl.spring.redis.dao;

import com.thl.spring.redis.model.RedisUserCounter;
import com.thl.spring.redis.model.RedisUserCounter;

import java.util.Optional;

public interface RedisDao {

    void save(String username, RedisUserCounter user);

    Optional<RedisUserCounter> findByUsername(String username);
}
