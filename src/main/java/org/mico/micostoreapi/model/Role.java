package org.mico.micostoreapi.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserRole> userRoles;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}