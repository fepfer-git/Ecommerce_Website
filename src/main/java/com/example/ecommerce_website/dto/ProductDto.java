package com.example.ecommerce_website.dto;

import com.example.ecommerce_website.entity.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Product} entity
 */
@Data
public class ProductDto {
    private int productId;
    private String productName;
    private String productDescription;
    private String status;
    private CategoryDto category;
    private List<RatingDto> ratings;
}