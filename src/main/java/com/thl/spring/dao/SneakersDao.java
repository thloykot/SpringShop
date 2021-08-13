package com.thl.spring.dao;


import com.thl.spring.model.Sneakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SneakersDao extends JpaRepository<Sneakers, Integer> {

    List<Sneakers> findSneakersByFirm(String firm);

    boolean existsByFirmAndAndSizeAndPrice(String firm, int size, int price);

    boolean existsById(int id);

    boolean existsByFirm(String firm);
}
