package com.example.ecommerce_website.dto.response;

import lombok.Data;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.ProductDetail} entity
 */
@Data
public class ProductDetailDtoResponseWithProduct {
    private int productDetailId;
    private int stock;
    private double price;
    private SizeDtoResponse size;
    private ProductDtoResponse product;
}