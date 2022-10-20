package com.example.ecommerce_website.dto.update;

import com.example.ecommerce_website.entity.Category;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
public class CategoryDto implements Serializable {
    private final int categoryId;
    private final String categoryName;
}