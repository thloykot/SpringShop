package com.thl.spring.controller;


import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/sneakers")
@RestController
public class SneakersRestController {

    private final SneakersService sneakersService;

    @PostMapping("/save")
    public ResponseEntity<Sneakers> save(@RequestBody Sneakers sneakers) {
        return sneakersService.save(sneakers);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Sneakers> findById(@PathVariable("id") int id) {
        return sneakersService.findById(id);
    }

    @GetMapping("/findByFirm/{firm}")
    public ResponseEntity<List<Sneakers>> findByFirm(@PathVariable("firm") String firm) {
        return sneakersService.findByFirm(firm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return sneakersService.delete(id);
    }

}
