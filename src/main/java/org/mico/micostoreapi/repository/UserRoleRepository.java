package org.mico.micostoreapi.repository;

import org.mico.micostoreapi.model.Role;
import org.mico.micostoreapi.model.User;
import org.mico.micostoreapi.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByUserId(Integer userId);
    Optional<UserRole> findFirstByUserId(Integer userId);
    boolean existsByUserAndRole(User user, Role role);
}