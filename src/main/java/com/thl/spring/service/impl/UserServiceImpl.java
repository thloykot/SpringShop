package com.thl.spring.service.impl;

import com.thl.spring.dao.UserDao;
import com.thl.spring.model.User;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        log.info("Finding user by username");

        return userDao.findByUsername(username);
    }

    @Override
    public User save(User user) throws Exception {
        Optional<User> userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB.isPresent()) {
            throw new Exception("User exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("saving user №{}", user.getId());
        return userDao.save(user);
    }

    @Override
    public User get(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public boolean isExists(String username) {
        return userDao.existsByUsername(username);
    }

}
