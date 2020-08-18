package com.project.crudspringbootproject.controller;

import com.project.crudspringbootproject.model.User;
import com.project.crudspringbootproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;


@Controller
public class UserController {

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user/user")
    public String getUserPage(Principal p, Model model) {
        model.addAttribute("user", p.getName());
        return "user/user";
    }
}
