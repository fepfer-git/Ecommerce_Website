package com.example.ecommerce_website.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Order} entity
 */
@Data
public class OrderDtoRequest{
    private int orderId;
    private Date orderDate;
    private String orderAddress;
    private String phone;
    private double totalPrice;
    private String userName;
    private List<OrderDetailDtoRequest> orderDetails;
}