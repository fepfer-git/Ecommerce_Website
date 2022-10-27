package com.example.ecommerce_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "size")

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

    @JsonIgnore
    @OneToMany(mappedBy = "size")
    private List<ProductDetail> productDetails = new ArrayList<>();

}
