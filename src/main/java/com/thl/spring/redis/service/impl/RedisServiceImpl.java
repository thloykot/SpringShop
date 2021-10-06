package com.thl.spring.redis.service.impl;

import com.thl.spring.redis.dao.impl.RedisDaoImpl;
import com.thl.spring.redis.model.RedisUserCounter;
import com.thl.spring.redis.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    private final RedisDaoImpl redisDao;
    private final ZoneId zoneId;

    @Override
    public void save(String username, RedisUserCounter user) {
        redisDao.save(username, user);
    }

    @Override
    public Optional<RedisUserCounter> find(String username) {
        return redisDao.findByUsername(username);
    }

    @Override
    public void setUserCounter(String username, int counter) {
        log.info("user {} has made request № {}", username, counter);
        redisDao.save(username, new RedisUserCounter(counter, Timestamp.from(ZonedDateTime.now(zoneId).toInstant())));
    }
}
