package com.thl.spring.controller;

import com.thl.spring.model.User;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistrationRestController {

    private final UserService userService;


    @PostMapping("/registration")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {

        if (userService.isExists(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {

        if (!userService.isExists(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.get(username));
    }
}
