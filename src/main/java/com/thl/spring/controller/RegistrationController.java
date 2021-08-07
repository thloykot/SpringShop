package com.thl.spring.controller;

import com.thl.spring.dao.UserDao;
import com.thl.spring.model.Role;
import com.thl.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    UserDao userDao;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        Optional<User> userFromBD = userDao.findByUsername(user.getUsername());

        if (userFromBD.isPresent()) {
            return "registration";
        }
        user.setActive(true);
        user.setUserRole(Collections.singleton(Role.USER));
        userDao.save(user);
        return "redirect:/login";
    }
}
