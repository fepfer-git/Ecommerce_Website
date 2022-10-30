package com.example.ecommerce_website.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.OrderDetail} entity
 */
@Data
public class OrderDetailDtoResponse {
    private int orderDetailId;
    private int quantity;
    private ProductDetailDtoResponse productDetail;
}