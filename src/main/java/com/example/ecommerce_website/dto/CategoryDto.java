package com.example.ecommerce_website.dto;

import com.example.ecommerce_website.entity.Category;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
public class CategoryDto {
    private String categoryName;
}