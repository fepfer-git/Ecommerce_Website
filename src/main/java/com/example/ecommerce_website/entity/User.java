package com.example.ecommerce_website.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "[user]")
public class User {
    @Id
    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "TEXT")
    private String userId;

    @Column(name = "user_name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "user_password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "user_email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
    private String status;

    @ManyToOne
    @JoinColumn(
            name = "role_id",
            nullable = false,
            referencedColumnName = "role_id",
            foreignKey = @ForeignKey(name = "role_user_fk")
    )
    private Role role;

    @OneToMany(mappedBy="user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

}
