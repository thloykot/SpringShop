package com.thl.spring.service;

import com.thl.spring.model.Sneakers;

import java.util.List;
import java.util.Optional;

public interface SneakersService {

    Sneakers save(Sneakers sneakers);

    Optional<Sneakers> findById(int id);

    List<Sneakers> findByFirm(String firm);

    boolean isExists(Sneakers sneakers);

    boolean isExistsById(int id);

    boolean isExistsByFirm(String firm);

    void delete(int id);

}
