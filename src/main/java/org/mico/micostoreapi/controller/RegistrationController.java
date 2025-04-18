package org.mico.micostoreapi.controller;

import org.mico.micostoreapi.model.Role;
import org.mico.micostoreapi.model.User;
import org.mico.micostoreapi.model.UserRole;
import org.mico.micostoreapi.repository.RoleRepository;
import org.mico.micostoreapi.repository.UserRepository;
import org.mico.micostoreapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        User savedUser = userRepository.save(user);

        Role defaultRole = roleRepository.findByName("ORDINARY")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(defaultRole);
        userRoleRepository.save(userRole);

        return ResponseEntity.ok("User registered successfully");
    }
}
