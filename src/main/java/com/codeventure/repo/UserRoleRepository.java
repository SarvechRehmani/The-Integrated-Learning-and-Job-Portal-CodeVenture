package com.codeventure.repo;

import com.codeventure.entities.Role;
import com.codeventure.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    public List<UserRole> findByRole(Role role);
}
