package com.example.ecommerce_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "image")
@Builder
public class Image {

    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    @Column(name = "image_id", updatable = false)
    private int imageId;

    @Column(name = "iamge_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "product_id",
            foreignKey = @ForeignKey(name = "product_image_fk")
    )
    private Product product;
}
