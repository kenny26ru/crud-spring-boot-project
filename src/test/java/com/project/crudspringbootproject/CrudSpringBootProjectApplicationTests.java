package com.project.crudspringbootproject;

import com.project.crudspringbootproject.model.Role;
import com.project.crudspringbootproject.model.User;
import com.project.crudspringbootproject.repository.RoleRepository;
import com.project.crudspringbootproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class CrudSpringBootProjectApplicationTests {
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void readUser() {
		User user = new User();
		user.setName("test1");
		user.setPassword(passwordEncoder.encode("test1"));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findRoleByRoleName("ROLE_USER"));
		user.setRoleSet(roles);
		userService.create(user);
	}
}
