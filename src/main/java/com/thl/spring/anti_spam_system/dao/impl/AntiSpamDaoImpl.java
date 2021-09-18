package com.thl.spring.anti_spam_system.dao.impl;

import com.thl.spring.anti_spam_system.dao.AntiSpamDao;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AntiSpamDaoImpl implements AntiSpamDao {

    private final HashOperations<String, String, Integer> hashOperations;

    private final String user = "USER";

    public AntiSpamDaoImpl(RedisTemplate<String, Integer> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(String username, int counter) {
        hashOperations.put(user, username, counter);
    }

    public Optional<Integer> findByUsername(String username) {
        return Optional.ofNullable(hashOperations.get(user, username));
    }

}
