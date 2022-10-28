package com.example.ecommerce_website.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Category} entity
 */
@Data
public class CategoryDtoResponse {
    private int categoryId;
    private String categoryName;
}