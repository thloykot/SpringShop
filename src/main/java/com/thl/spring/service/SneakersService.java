package com.thl.spring.service;

import com.thl.spring.model.Sneakers;

import java.util.List;

public interface SneakersService {

    void save(Sneakers sneakers);

    Sneakers findById(int id);

    List<Sneakers> findByName(String firm);

    void delete(int id);

}
