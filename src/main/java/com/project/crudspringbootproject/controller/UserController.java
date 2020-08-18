package com.project.crudspringbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }
}
