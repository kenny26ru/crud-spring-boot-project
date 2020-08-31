package com.project.crudspringbootproject;

import com.project.crudspringbootproject.model.Role;
import com.project.crudspringbootproject.model.User;
import com.project.crudspringbootproject.repository.RoleRepository;
import com.project.crudspringbootproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
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
	void add() {
		User user = new User();
		user.setName("admin");
		user.setSecondName("Adminovich");
		user.setEmail("admin@mail.ru");
		user.setAge(15);
		user.setPassword(passwordEncoder.encode("admin"));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findRoleByRoleName("ADMIN"));
		user.setRoleSet(roles);
		userService.create(user);
		System.out.println(user);
	}

	@Test
	void addTest() {
		User user = new User();
		user.setName("test");
		user.setSecondName("Testovich");
		user.setEmail("test@mail.ru");
		user.setAge(15);
		user.setPassword(passwordEncoder.encode("test"));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findRoleByRoleName("USER"));
		user.setRoleSet(roles);
		userService.create(user);
		System.out.println(user);
	}

	@Test
	void load(Principal p){
		UserDetails user = userService.findUserByName(p.getName());
		System.out.println(user.getAuthorities());
		System.out.println(user.getUsername());
	}
 }
