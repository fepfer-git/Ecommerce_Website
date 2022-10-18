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
@Table(name = "[rating]")
public class Rating {

    @EmbeddedId
    RatingKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "rating", nullable = false, columnDefinition = "INT")
    private int rating;
}
