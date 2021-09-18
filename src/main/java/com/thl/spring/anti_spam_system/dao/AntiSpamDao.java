package com.thl.spring.anti_spam_system.dao;

import java.util.Optional;

public interface AntiSpamDao {

    void save(String username, int counter);

    Optional<Integer> findByUsername(String Username);
}
