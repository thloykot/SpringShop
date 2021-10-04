package com.thl.spring.redis.dao.impl;

import com.thl.spring.redis.dao.RedisDao;
import com.thl.spring.redis.model.RedisUserCounter;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RedisDaoImpl implements RedisDao {

    private final HashOperations<String, String, RedisUserCounter> hashOperations;
    private final static String USER = "USER";

    public RedisDaoImpl(RedisTemplate<String, RedisUserCounter> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(String username, RedisUserCounter user) {
        hashOperations.put(USER, username, user);
    }

    public Optional<RedisUserCounter> findByUsername(String username) {
        return Optional.ofNullable(hashOperations.get(USER, username));
    }

}
