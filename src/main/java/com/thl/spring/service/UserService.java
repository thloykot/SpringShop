package com.thl.spring.service;

import com.thl.spring.dto.UserDto;
import com.thl.spring.model.UserEntity;

import java.util.Optional;

public interface UserService {

    void save(UserDto userDto);

    Optional<UserEntity> findByUsername(String username);

}
