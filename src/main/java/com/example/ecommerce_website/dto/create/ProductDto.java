package com.example.ecommerce_website.dto.create;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Product} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private int productId;

    @NotBlank(message = "Product name cannot be empty!")
    private String productName;

    private String productDescription;

    private int categoryId;
}