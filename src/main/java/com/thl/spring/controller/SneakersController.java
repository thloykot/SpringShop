package com.thl.spring.controller;


import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/sneakers")
@RestController
public class SneakersController {

    private final SneakersService sneakersService;

    @PostMapping("/save")
    public ResponseEntity<Sneakers> save(@RequestBody Sneakers sneakers) {
        return sneakersService.save(sneakers);
    }

    @GetMapping("/findById")
    public ResponseEntity<Sneakers> find(@RequestParam("id") int id) {
        return sneakersService.findById(id);
    }

    @GetMapping("/findByFirm")
    public ResponseEntity<List<Sneakers>> findById(@RequestParam("firm") String firm) {
        return sneakersService.findByFirm(firm);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") int id) {
        return sneakersService.delete(id);
    }

}
