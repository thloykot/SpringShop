package com.thl.spring.controller;


import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sneakers")
@RestController
public class SneakersController {

    @Autowired
    private SneakersService sneakersService;

    @GetMapping(value = "/save")
    public void save(@RequestParam("firm") String firm, @RequestParam("size") int size, @RequestParam("price") int price) {
        Sneakers sneakers = new Sneakers(firm, size, price);
        sneakersService.save(sneakers);
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<Sneakers> find(@RequestParam("id") int id) {
        return ResponseEntity.ok().body(sneakersService.findById(id));
    }

    @GetMapping(value = "/findByName")
    public ResponseEntity<List<Sneakers>> findById(@RequestParam("firm") String firm) {
        return ResponseEntity.ok().body(sneakersService.findByName(firm));
    }

    @GetMapping(value = "/delete")
    public void delete(@RequestParam("id") int id) {
        sneakersService.delete(id);
    }

}
