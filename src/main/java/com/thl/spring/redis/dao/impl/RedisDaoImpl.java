package com.thl.spring.redis.dao.impl;

import com.thl.spring.redis.dao.RedisDao;
import com.thl.spring.redis.model.RedisUser;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RedisDaoImpl implements RedisDao {

    private final HashOperations<String, String, RedisUser> hashOperations;

    private final String username = "USER";

    public RedisDaoImpl(RedisTemplate<String, RedisUser> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(String username, RedisUser user) {
        hashOperations.put(username, username, user);
    }

    public Optional<RedisUser> findByUsername(String username) {
        return Optional.ofNullable(hashOperations.get(username, username));
    }

}
