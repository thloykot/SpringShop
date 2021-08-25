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
public class SneakersController {

    private final SneakersService sneakersService;

    @PutMapping()
    public ResponseEntity<Integer> save(@RequestBody Sneakers sneakers) {
        return ResponseEntity.ok(sneakersService.save(sneakers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sneakers> findById(@PathVariable("id") int id) {
        return sneakersService.findById(id).
                map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/firms/{firm}")
    public ResponseEntity<List<Sneakers>> findByFirm(@PathVariable("firm") String firm) {
        return ResponseEntity.ok(sneakersService.findByFirm(firm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return sneakersService.delete(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
