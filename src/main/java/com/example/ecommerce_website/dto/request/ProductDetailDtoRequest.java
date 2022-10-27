package com.example.ecommerce_website.dto.request;

import com.example.ecommerce_website.entity.Product;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.ProductDetail} entity
 */
@Data
public class ProductDetailDtoRequest {
    private int stock;
    private double price;
    private int sizeId;
    private Product product;
}