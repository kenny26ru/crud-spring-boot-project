package com.project.crudspringbootproject.controller;

import com.project.crudspringbootproject.dto.UserDto;
import com.project.crudspringbootproject.model.Role;
import com.project.crudspringbootproject.model.User;
import com.project.crudspringbootproject.repository.RoleRepository;
import com.project.crudspringbootproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest")
public class NewRestController {
    private final UserService userService;
    private final RoleRepository roleRepository;


    public NewRestController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSecondName(userDto.getSecondName());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByRoleName(userDto.getRoleSet()));

        user.setRoleSet(roles);
        userService.create(userService.encodePassword(user));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        User user = userService.getUserById(id);
        user.setName(userDto.getName());
        user.setSecondName(userDto.getSecondName());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByRoleName(userDto.getRoleSet()));

        user.setRoleSet(roles);
        userService.create(userService.encodePassword(user));
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}




