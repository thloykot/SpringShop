package com.thl.spring.redis.dao;

import com.thl.spring.redis.model.RedisUser;

import java.util.Optional;

public interface RedisDao {

    void save(String username, RedisUser user);

    Optional<RedisUser> findByUsername(String Username);
}
