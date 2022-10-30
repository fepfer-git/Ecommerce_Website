package com.example.ecommerce_website.services.interfaces;
import com.example.ecommerce_website.dto.request.OrderDetailDtoRequest;
import com.example.ecommerce_website.entity.Order;
import com.example.ecommerce_website.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    double getPrice (OrderDetailDtoRequest orderDetail);
    List<OrderDetail> addOrderDetails (List<OrderDetailDtoRequest> orderDetailDtoRequest, Order order);
}
