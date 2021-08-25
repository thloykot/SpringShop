package com.thl.spring.service;

import com.thl.spring.model.Sneakers;

import java.util.List;
import java.util.Optional;

public interface SneakersService {


    int save(Sneakers sneakers);

    Optional<Sneakers> findById(int id);

    List<Sneakers> findByFirm(String firm);

    boolean delete(int id);

}
