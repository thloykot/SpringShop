package com.thl.spring.service.impl;

import com.thl.spring.dao.SneakersDao;
import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class SneakersServiceImpl implements SneakersService {

    private final SneakersDao sneakersDao;

    @Override
    public ResponseEntity<Sneakers> save(Sneakers sneakers) {
        log.info("Saving new sneakers {}", sneakers.getFirm());
        return ResponseEntity.ok().body(sneakersDao.save(sneakers));
    }

    @Override
    public ResponseEntity<Sneakers> findById(int id) {
        log.info("Finding sneakers by id:{}", id);
        return ResponseEntity.ok().body(sneakersDao.findById(id).get());
    }

    @Override
    public ResponseEntity<List<Sneakers>> findByFirm(String firm) {
        List<Sneakers> sneakersList = sneakersDao.findSneakersByFirm(firm);
        log.info("{} sneakers of firm {} found", sneakersList.size(), firm);
        return ResponseEntity.ok().body(sneakersList);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        log.info("Deleting sneakers with id:{}", id);
        sneakersDao.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
