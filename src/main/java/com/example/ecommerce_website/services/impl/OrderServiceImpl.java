package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.OrderDetailDtoRequest;
import com.example.ecommerce_website.dto.request.OrderDtoRequest;
import com.example.ecommerce_website.dto.response.OrderDtoResponse;
import com.example.ecommerce_website.entity.Order;
import com.example.ecommerce_website.entity.OrderDetail;
import com.example.ecommerce_website.entity.ProductDetail;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.exception.OutOfStockException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.OrderRepository;
import com.example.ecommerce_website.repository.ProductDetailRepository;
import com.example.ecommerce_website.repository.UserRepository;
import com.example.ecommerce_website.services.interfaces.IOrderDetailService;
import com.example.ecommerce_website.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public OrderDtoRequest createNewOrder(OrderDtoRequest orderDtoRequest) {
        double totalOrderPrice = 0;
        List<OrderDetailDtoRequest> orderDetails = orderDtoRequest.getOrderDetails();
        for (OrderDetailDtoRequest orderDetailDtoRequest : orderDetails) {
            ProductDetail productDetail = productDetailRepository.findById(orderDetailDtoRequest.getProductDetailId()).get();
            if (productDetail.getStock() < orderDetailDtoRequest.getQuantity()){
                throw new OutOfStockException("Product out of stock!");
            }
            totalOrderPrice += orderDetailService.getPrice(orderDetailDtoRequest);
        }


        String status = "Active";
        Date nowDate = new Date(System.currentTimeMillis());

        Order order = Order.builder()
                .orderAddress(orderDtoRequest.getOrderAddress())
                .orderDate(nowDate)
                .status(status)
                .totalPrice(totalOrderPrice)
                .phone(orderDtoRequest.getPhone())
                .user(userRepository.findByUserName(orderDtoRequest.getUserName()))
                .build();

        Order savedOrder = orderRepository.save(order);
        orderDetailService.addOrderDetails(orderDtoRequest.getOrderDetails(), savedOrder);

        return objectMapperUtils.map(savedOrder, OrderDtoRequest.class);
    }

    @Override
    public List<OrderDtoResponse> getAllOrdersByUserName(String userName) {
        List<Order> orderListFound = orderRepository.findAllByUserUserName(userName);
        if(orderListFound.isEmpty()){
            throw new NotFoundException("Username doesn't exist!");
        }

        return objectMapperUtils.mapAll(orderListFound, OrderDtoResponse.class);
    }
}
