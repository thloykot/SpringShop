package com.thl.spring.redis.service.impl;

import com.thl.spring.redis.dao.impl.UserCounterDaoImpl;
import com.thl.spring.redis.model.UserCounter;
import com.thl.spring.redis.service.UserCounterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class UserCounterServiceImpl implements UserCounterService {

    private final UserCounterDaoImpl redisDao;


    @Override
    public void save(String username, UserCounter userCounter) {
        redisDao.save(username, userCounter);
    }

    @Override
    public Optional<UserCounter> find(String username) {
        return redisDao.findByUsername(username);
    }

    @Override
    public void set(String username, int counter) {
        log.info("user {} has made request № {}", username, counter);
        redisDao.save(username, new UserCounter(counter, ZonedDateTime.now(ZoneOffset.UTC).toInstant().toEpochMilli()));
    }
}
