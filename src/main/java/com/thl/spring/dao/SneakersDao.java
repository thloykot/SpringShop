package com.thl.spring.dao;


import com.thl.spring.model.Sneakers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SneakersDao extends CrudRepository<Sneakers, Integer> {
    //Jpa is updated CRUD
    List<Sneakers> findSneakersByFirm(String firm);
}
