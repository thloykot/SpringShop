package com.thl.spring.service.impl;

import com.thl.spring.dao.UserDao;
import com.thl.spring.model.User;
import com.thl.spring.model.UserAdapter;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import javax.websocket.Session;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findByUsername(username);
        if(userOptional.isEmpty()){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),user.getRoles());
    }
}
