package com.thl.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationWebController {

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
