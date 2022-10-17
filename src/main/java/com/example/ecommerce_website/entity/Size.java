package com.example.ecommerce_website.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "tbl_size")

public class Size {
    @Id
    @SequenceGenerator(
            name = "size_sequence",
            sequenceName = "size_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "size_sequence"
    )
    @Column(name = "size_id", updatable = false)
    private int sizeId;

    @Column(name = "size_name", nullable = false, columnDefinition = "VARCHAR(10)")
    private String sizeName;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
    private String status;

    @OneToMany(mappedBy = "size")
    private List<ProductDetail> productDetails = new ArrayList<>();

}
