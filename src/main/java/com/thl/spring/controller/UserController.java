package com.thl.spring.controller;

import com.thl.spring.dto.UserDto;
import com.thl.spring.model.UserEntity;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @PutMapping("/register")
    public ResponseEntity<Integer> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("username") String username) {
        return userService.findByUsername(username).
                map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.notFound().build());
    }
}
