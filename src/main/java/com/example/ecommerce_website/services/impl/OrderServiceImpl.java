package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.OrderDetailDtoRequest;
import com.example.ecommerce_website.dto.request.OrderDtoRequest;
import com.example.ecommerce_website.dto.response.OrderDtoResponse;
import com.example.ecommerce_website.entity.*;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.exception.OutOfStockException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.OrderRepository;
import com.example.ecommerce_website.repository.ProductDetailRepository;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.repository.UserRepository;
import com.example.ecommerce_website.services.interfaces.IOrderDetailService;
import com.example.ecommerce_website.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private ProductRepository productRepository;
    @Override
    public OrderDtoRequest createNewOrder(OrderDtoRequest orderDtoRequest) {
        double totalOrderPrice = 0;
        List<OrderDetailDtoRequest> orderDetails = orderDtoRequest.getOrderDetails();
        for (OrderDetailDtoRequest orderDetailDtoRequest : orderDetails) {

            Optional<ProductDetail>  productDetail = productDetailRepository.findById(orderDetailDtoRequest.getProductDetailId());
            if(productDetail.isEmpty()){
                throw new NotFoundException("ProductDetail with id: "+ orderDetailDtoRequest.getProductDetailId() + " cannot be found!");
            }
            if (productDetail.get().getStock() < orderDetailDtoRequest.getQuantity()){
                Product product = productRepository.findById(productDetail.get().getProduct().getProductId()).orElse(new Product());
                throw new OutOfStockException("Product '" + product.getProductName() + "' with size '" + productDetail.get().getSize().getSizeName() + "' is only have " + productDetail.get().getStock() +" items left!");
            }
            totalOrderPrice += orderDetailService.getPrice(orderDetailDtoRequest);
        }


        String status = "Active";
        Date nowDate = new Date(System.currentTimeMillis());
        User orderUser = userRepository.findByUserName(orderDtoRequest.getUserName());
        if(orderUser == null){
            throw new NotFoundException("User is not found!");
        }

        Order order = Order.builder()
                .orderAddress(orderDtoRequest.getOrderAddress())
                .orderDate(nowDate)
                .status(status)
                .totalPrice(totalOrderPrice)
                .phone(orderDtoRequest.getPhone())
                .user(orderUser)
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
