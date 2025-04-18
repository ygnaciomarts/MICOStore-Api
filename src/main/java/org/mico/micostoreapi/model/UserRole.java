package org.mico.micostoreapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole() {}

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public Integer getId() { return id; }
    public User getUser() { return user; }
    public Role getRole() { return role; }

    public void setId(Integer id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setRole(Role role) { this.role = role; }
}

// Role.java debe estar también creado como te mostré antes
// Y User.java debe tener algo así:
// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
// private List<UserRole> roles;
