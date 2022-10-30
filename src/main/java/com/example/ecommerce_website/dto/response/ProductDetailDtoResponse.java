package com.example.ecommerce_website.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.ProductDetail} entity
 */
@Data
public class ProductDetailDtoResponse {
    private int productDetailId;
    private int stock;
    private double price;
    private SizeDtoResponse size;
}