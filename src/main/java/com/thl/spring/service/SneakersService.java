package com.thl.spring.service;

import com.thl.spring.model.Sneakers;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SneakersService {

    ResponseEntity<Sneakers> save(Sneakers sneakers);

    ResponseEntity<Sneakers> findById(int id);

    ResponseEntity<List<Sneakers>> findByFirm(String firm);

    ResponseEntity<?> delete(int id);

}
