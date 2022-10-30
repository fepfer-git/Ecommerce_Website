package com.example.ecommerce_website.dto.request;

import lombok.Data;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.OrderDetail} entity
 */
@Data
public class OrderDetailDtoRequest {
    private int quantity;
    private int productDetailId;
}