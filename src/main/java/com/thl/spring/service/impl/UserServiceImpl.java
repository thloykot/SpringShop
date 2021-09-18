package com.thl.spring.service.impl;

import com.thl.spring.anti_spam_system.AntiSpam;
import com.thl.spring.dao.UserDao;
import com.thl.spring.dto.UserDto;
import com.thl.spring.model.UserEntity;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final AntiSpam antiSpam;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    @Override
    public int save(UserDto userDto) {
        log.info("Saving or updating user");
        return Objects.requireNonNull(userDao.save(
                userDao.findIdByUsername(userDto.getUsername()).
                        map(idOnly -> {
                            antiSpam.userMadeActivity(SecurityContextHolder.getContext());
                            return toUserEntity(idOnly.getId(), userDto);
                        }).
                        orElseGet(() -> {
                            antiSpam.generateCounter(userDto.getUsername());
                            return toUserEntity(userDto);
                        }))).getId();
    }


    @Override
    public Optional<UserEntity> findByUsername(String username) {
        log.info("Finding user by username");
        antiSpam.userMadeActivity(SecurityContextHolder.getContext());
        return userDao.findByUsername(username);
    }

    private UserEntity toUserEntity(int id, UserDto userDto) {
        return new UserEntity(id,
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getRole());
    }

    private UserEntity toUserEntity(UserDto userDto) {
        return new UserEntity(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getRole());
    }
}
