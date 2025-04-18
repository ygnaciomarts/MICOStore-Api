package org.mico.micostoreapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String gender;
    private LocalDateTime createdAt;
    private List<String> roles;

    // Constructor
    public UserDTO(Integer id, String username, String email, String password, String name, String lastname, String gender, LocalDateTime createdAt, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.createdAt = createdAt;
        this.roles = roles;
    }

    // Getters
    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getLastname() { return lastname; }
    public String getGender() { return gender; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<String> getRoles() { return roles; }
}