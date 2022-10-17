package com.example.ecommerce_website.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "tbl_role")

public class Role {
    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column(name = "role_id", updatable = false)
    private int roleId;

    @Column(name = "role_name", nullable = false, columnDefinition = "VARCHAR(10)")
    private String roleName;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
    private String status;
}
