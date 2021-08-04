package com.thl.spring.dao;


import com.thl.spring.model.Sneakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakersDao extends JpaRepository<Sneakers, Integer> {
    //Jpa is updated CRUD
}
