package com.thl.spring.anti_spam_system.service;

import java.util.Optional;

public interface AntiSpamService {

    void save(String username, int counter);

    Optional<Integer> find(String username);
}
