package com.project.crudspringbootproject.service;

import com.project.crudspringbootproject.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> allUsers();

    User create(User user);

    void delete(Long id);

    User findUserByName(String userName);

    User encodePassword(User user);
}
