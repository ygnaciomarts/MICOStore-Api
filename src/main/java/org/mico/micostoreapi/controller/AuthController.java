package org.mico.micostoreapi.controller;

import org.mico.micostoreapi.auth.JwtResponse;
import org.mico.micostoreapi.auth.JwtUtil;
import org.mico.micostoreapi.dto.UserDTO;
import org.mico.micostoreapi.model.LoginRequest;
import org.mico.micostoreapi.model.User;
import org.mico.micostoreapi.repository.UserRepository;
import org.mico.micostoreapi.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        User user = optionalUser.get();
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().getName())
                .collect(Collectors.toList());

        String token = jwtUtil.generateToken(user.getUsername());

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                null, // Do not return password
                user.getName(),
                user.getLastname(),
                user.getGender(),
                user.getCreatedAt(),
                roles
        );

        return ResponseEntity.ok(new JwtResponse(token, userDTO));
    }
}
