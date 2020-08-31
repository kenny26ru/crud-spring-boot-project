package com.project.crudspringbootproject.controller;

import com.project.crudspringbootproject.model.Role;
import com.project.crudspringbootproject.model.User;
import com.project.crudspringbootproject.repository.RoleRepository;
import com.project.crudspringbootproject.repository.UserRepository;
import com.project.crudspringbootproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;


@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/user/user")
    public String getUserPage(Principal p, Model model) {
        User user = userRepository.findUserByName(p.getName());
        Set<Role> roles = user.getRoleSet();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user/user";
    }

    @GetMapping(value = "/logout")
    public String lohout() {
        return "redirect:/login";
    }
}
