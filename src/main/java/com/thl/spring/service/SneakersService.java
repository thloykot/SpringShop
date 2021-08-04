package com.thl.spring.service;

import com.thl.spring.model.Sneakers;

public interface SneakersService {

    void save(Sneakers sneakers);

    Sneakers findById(int id);

    void delete(int id);

}
