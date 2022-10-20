package com.example.ecommerce_website.dto.update;

import lombok.Data;

import java.util.List;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Product} entity
 */
@Data
public class ProductDtoUpdate {
    private int productId;
    private String productName;
    private String productDescription;
    private CategoryDto category;
    private List<ImageDto> images;
}