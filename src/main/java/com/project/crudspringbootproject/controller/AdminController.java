package com.project.crudspringbootproject.controller;

import com.project.crudspringbootproject.model.Role;
import com.project.crudspringbootproject.model.User;
import com.project.crudspringbootproject.repository.RoleRepository;
import com.project.crudspringbootproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin/users")
    public String allUsers(Model model) {
        List<User> userList = userService.allUsers();
        model.addAttribute("users", userList);
        return "admin/userList";
    }

    @GetMapping(value = "/admin/add")
    public String addUserFrom(User user, Model model) {
        List<Role> roleSet = roleRepository.findAll();
        model.addAttribute("roles", roleSet);
        model.addAttribute("user", user);
        return "admin/addPage";
    }

    @PostMapping("/admin/add")
    public String addUser(User user) {
        userService.create(userService.encodePassword(user));
        return "redirect:/admin/users";
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
        return "redirect:/admin/users";
    }

//    @PostMapping
//    public String editUser(User user){
//        userService.
//    }

    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
//        User user = userService.getUserById(id);
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
