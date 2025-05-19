package org.mico.micostoreapi.controller;

import org.mico.micostoreapi.dto.RegisterDTO;
import jakarta.validation.Valid;
import org.mico.micostoreapi.model.Role;
import org.mico.micostoreapi.model.User;
import org.mico.micostoreapi.model.UserRole;
import org.mico.micostoreapi.repository.RoleRepository;
import org.mico.micostoreapi.repository.UserRepository;
import org.mico.micostoreapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setGender(registerDTO.getGender());
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        Role defaultRole = roleRepository.findByName("user")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        if (!userRoleRepository.existsByUserAndRole(savedUser, defaultRole)) {
            UserRole userRole = new UserRole();
            userRole.setUser(savedUser);
            userRole.setRole(defaultRole);
            userRoleRepository.save(userRole);
        }

        return ResponseEntity.ok("User registered successfully");
    }
}
