package com.project.crudspringbootproject.repository;

import com.project.crudspringbootproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("select user from User user where user.name= :user_name")
    User findUserByName(String name);

}
