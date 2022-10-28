package com.example.ecommerce_website.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Category} entity
 */
@Data
public class CategoryDtoRequest {
    private int categoryId;
    private String categoryName;
    private String status;
}