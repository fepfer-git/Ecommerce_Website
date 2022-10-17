package com.example.ecommerce_website.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RatingKey implements Serializable {
    @Column(name = "user_id")
    String userId;

    @Column(name = "product_id")
    int productId;
}
