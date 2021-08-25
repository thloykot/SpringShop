package com.thl.spring.dao;

import com.thl.spring.dao.criteriaqueries.IdOnly;
import com.thl.spring.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Optional<IdOnly> findIdByUsername(String username);

}
