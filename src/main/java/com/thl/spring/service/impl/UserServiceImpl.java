package com.thl.spring.service.impl;

import com.thl.spring.dao.UserDao;
import com.thl.spring.dto.UserDto;
import com.thl.spring.model.UserEntity;
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
    public void save(UserDto userDto) {
        Optional<UserEntity> userEntityOptional = userDao.findByUsername(userDto.getUsername());
        if (userEntityOptional.isPresent()) {
            log.info("Updating user");
            userDao.save(updateUser(userEntityOptional.get(), userDto));
        } else {
            UserEntity userEntity = toEntity(userDto);
            log.info("Saving user");
            userDao.save(userEntity);
        }
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    private UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRole(userDto.getRole());
        return userEntity;
    }

    private UserEntity updateUser(UserEntity userEntity, UserDto userDto) {
        userEntity.setRole(userDto.getRole());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }

}
