package com.example.ecommerce_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "[user]")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id", updatable = false, columnDefinition = "Int")
    private int userId;

    @Column(name = "user_name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String userName;

    @Column(name = "user_full_name", nullable = false, columnDefinition = "TEXT")
    private String fullName;

    @Column(name = "user_password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "user_email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
    private String status;

    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(10)")
    private String role;

    @OneToMany(mappedBy="user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

}
