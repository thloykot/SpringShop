package com.thl.spring.controller;

import com.thl.spring.model.User;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistrationRestController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    @PostMapping("/registration")
    public void addUser(User user) throws Exception {
        Optional<User> userFromBD = userService.findByUsername(user.getUsername());

        if (userFromBD.isPresent()) {
            throw new Exception("User exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }
}
