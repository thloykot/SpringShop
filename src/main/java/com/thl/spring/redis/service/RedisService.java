package com.thl.spring.redis.service;

import com.thl.spring.redis.model.RedisUser;

import java.util.Date;
import java.util.Optional;

public interface RedisService {

    void save(String username, RedisUser user);

    Optional<RedisUser> find(String username);

    Date getUnlockDate(String username);
}
