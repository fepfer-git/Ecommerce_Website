package com.example.ecommerce_website.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Order} entity
 */
@Data
public class OrderDtoRequest{
    private int orderId;
    private Date orderDate;
    @NotBlank(message = "Address cannot be empty!")
    private String orderAddress;
    @Length(min = 10, max = 11, message = "Phone number is invalid!")
    private String phone;
    private double totalPrice;
    private String userName;
    private List<OrderDetailDtoRequest> orderDetails;
}