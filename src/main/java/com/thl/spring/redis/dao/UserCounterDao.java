package com.thl.spring.redis.dao;

import com.thl.spring.redis.model.UserCounter;

import java.util.Optional;

public interface UserCounterDao {

    void save(String username, UserCounter userCounter);

    Optional<UserCounter> findByUsername(String username);
}
