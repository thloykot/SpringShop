package com.thl.spring.redis.dao.impl;

import com.thl.spring.redis.dao.UserCounterDao;
import com.thl.spring.redis.model.UserCounter;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserCounterDaoImpl implements UserCounterDao {

    private final HashOperations<String, String, UserCounter> hashOperations;
    private final static String USER = "USER";

    public UserCounterDaoImpl(RedisTemplate<String, UserCounter> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(String username, UserCounter userCounter) {
        hashOperations.put(USER, username, userCounter);
    }

    public Optional<UserCounter> findByUsername(String username) {
        return Optional.ofNullable(hashOperations.get(USER, username));
    }

}
