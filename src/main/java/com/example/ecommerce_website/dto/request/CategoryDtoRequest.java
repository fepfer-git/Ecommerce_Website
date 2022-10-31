package com.example.ecommerce_website.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Category} entity
 */
@Data
public class CategoryDtoRequest {
    private int categoryId;

    @NotBlank(message = "Category name cannot be empty!")
    private String categoryName;
    private String status;
}