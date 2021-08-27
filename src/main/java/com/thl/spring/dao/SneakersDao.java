package com.thl.spring.dao;


import com.thl.spring.dao.criteriaqueries.IdOnly;
import com.thl.spring.model.Sneakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SneakersDao extends JpaRepository<Sneakers, Integer> {

    List<Sneakers> findSneakersByFirm(String firm);

    Optional<IdOnly> findIdByFirmAndModel(String firm, String model);

    int deleteSneakersById(Integer id);

}
