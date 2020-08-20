package com.project.crudspringbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping(value = "/logout")
    public String lohout(){
        return "redirect:/login";
    }
}
