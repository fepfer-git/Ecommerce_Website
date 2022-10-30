package com.example.ecommerce_website.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Order} entity
 */
@Data
public class OrderDtoResponse {
    private int orderId;
    private String orderAddress;
    private String phone;
    private double totalPrice;
    private List<OrderDetailDtoResponse> orderDetails;
}