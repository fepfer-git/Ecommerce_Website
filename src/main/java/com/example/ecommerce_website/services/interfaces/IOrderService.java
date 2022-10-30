package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.request.OrderDtoRequest;
import com.example.ecommerce_website.dto.response.OrderDtoResponse;

import java.util.List;

public interface IOrderService {
    OrderDtoRequest createNewOrder(OrderDtoRequest orderDtoRequest);

    List<OrderDtoResponse> getAllOrdersByUserName(String userName);
}
