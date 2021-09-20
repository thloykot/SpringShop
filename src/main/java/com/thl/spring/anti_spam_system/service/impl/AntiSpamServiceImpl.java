package com.thl.spring.anti_spam_system.service.impl;

import com.thl.spring.anti_spam_system.dao.impl.AntiSpamDaoImpl;
import com.thl.spring.anti_spam_system.service.AntiSpamService;
import com.thl.spring.dao.UserDao;
import com.thl.spring.model.Role;
import com.thl.spring.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AntiSpamServiceImpl implements AntiSpamService {

    private final AntiSpamDaoImpl antiSpamDao;
    private  final UserDao userDao;

    @Override
    public void save(String username,int counter) {
        antiSpamDao.save(username, counter);
    }

    @Override
    public Optional<Integer> find(String username) {
        return antiSpamDao.findByUsername(username);
    }

    public UserEntity blockUser(String username){
        return userDao.findByUsername(username).map(userEntity -> {
            userEntity.setRole(Collections.singleton(Role.BANNED));
            return userDao.save(userEntity);
        }).orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }
}
