package com.example.ecommerce_website.entity;

import com.example.ecommerce_website.dto.response.OrderDetailDtoResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link Order} entity
 */
@Data
public class OrderDto implements Serializable {
    private final int orderId;
    private final Date orderDate;
    private final String orderAddress;
    private final String phone;
    private final double totalPrice;
    private final List<OrderDetailDtoResponse> orderDetails;
}