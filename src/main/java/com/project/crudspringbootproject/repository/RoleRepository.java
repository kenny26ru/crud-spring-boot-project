package com.project.crudspringbootproject.repository;

import com.project.crudspringbootproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRoleName(String roleName);
}
