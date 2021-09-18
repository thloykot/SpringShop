package com.thl.spring.anti_spam_system.service.impl;

import com.thl.spring.anti_spam_system.dao.impl.AntiSpamDaoImpl;
import com.thl.spring.anti_spam_system.service.AntiSpamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AntiSpamServiceImpl implements AntiSpamService {

    private final AntiSpamDaoImpl antiSpamDao;

    @Override
    public void save(String username,int counter) {
        antiSpamDao.save(username, counter);
    }

    @Override
    public Optional<Integer> find(String username) {
        return antiSpamDao.findByUsername(username);
    }
}
