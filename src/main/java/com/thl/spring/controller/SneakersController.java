package com.thl.spring.controller;


import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/sneakers")
@RestController
public class SneakersController {

    private final SneakersService sneakersService;

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
