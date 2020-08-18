package com.project.crudspringbootproject.service;

import com.project.crudspringbootproject.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> allUsers();

    User create(User user);

    void delete(Long id);

    User findUserByName(String userName);

    User encodePassword(User user);
 }
