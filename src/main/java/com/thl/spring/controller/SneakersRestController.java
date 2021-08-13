package com.thl.spring.controller;


import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/sneakers")
@RestController
public class SneakersRestController {

    private final SneakersService sneakersService;

    @PostMapping("/save")
    public ResponseEntity<Sneakers> save(@RequestBody Sneakers sneakers) {

        if (sneakersService.isExists(sneakers)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        sneakersService.save(sneakers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Sneakers> findById(@PathVariable("id") int id) {
        Optional<Sneakers> optionalSneakers = sneakersService.findById(id);

        if (optionalSneakers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalSneakers.get());
    }

    @GetMapping("/findByFirm/{firm}")
    public ResponseEntity<List<Sneakers>> findByFirm(@PathVariable("firm") String firm) {

        if (!sneakersService.isExistsByFirm(firm)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(sneakersService.findByFirm(firm));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id) {
        if (!sneakersService.isExistsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        sneakersService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
