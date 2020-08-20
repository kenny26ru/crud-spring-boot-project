package com.project.crudspringbootproject.controller;

import com.project.crudspringbootproject.model.Role;
import com.project.crudspringbootproject.model.User;
import com.project.crudspringbootproject.repository.RoleRepository;
import com.project.crudspringbootproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin/admin")
    public String allUsers(Principal p, Model model) {
        User user = userService.findUserByName(p.getName());
        Set<Role> roles = user.getRoleSet();
        List<User> userList = userService.allUsers();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("users", userList);
        return "admin/adminPage";
    }

    @GetMapping(value = "/admin/add")
    public String addUserFrom(User user, Model model) {
        List<Role> roleSet = roleRepository.findAll();
        model.addAttribute("roles", roleSet);
        model.addAttribute("newUser", user);
        return "admin/addPage";
    }

    @PostMapping("/admin/add")
    public String addUser(User user) {
        userService.create(userService.encodePassword(user));
        return "redirect:/admin/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/editPage";
    }

    @PostMapping("/admin/edit")
    public String editUser(User user) {
        userService.create(user);
        return "redirect:/admin/admin";
    }

    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/admin";
    }
}
