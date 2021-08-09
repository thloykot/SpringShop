package com.thl.spring.controller;

import com.thl.spring.dao.UserDao;
import com.thl.spring.model.Role;
import com.thl.spring.model.User;
import com.thl.spring.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user, Model model) {
        Optional<User> userFromBD = userService.findByUsername(user.getUsername());

        if (userFromBD.isPresent()) {
            model.addAttribute("existedUsername", model);
            return "registration";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }
}
