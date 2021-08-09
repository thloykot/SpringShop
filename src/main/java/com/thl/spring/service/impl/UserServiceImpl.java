package com.thl.spring.service.impl;

import com.thl.spring.dao.UserDao;
import com.thl.spring.model.User;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public Optional<User> findByUsername(String login) {
        log.info("Finding user by username");
        return userDao.findByUsername(login);
    }

    @Override
    public void saveUser(User user) {
        log.info("saving user №{}", user.getId());
        userDao.save(user);
    }
}
