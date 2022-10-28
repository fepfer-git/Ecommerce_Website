package com.example.ecommerce_website.dto.response;

import com.example.ecommerce_website.entity.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Product} entity
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {
    private int productId;
    private String productName;
    private String productDescription;
    private CategoryDtoResponse category;
    private List<ImageDtoResponse> images;
    private List<ProductDetailDtoResponse> productDetails;
    private List<Rating> ratings;
}