package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.OrderDetailDtoRequest;
import com.example.ecommerce_website.entity.Order;
import com.example.ecommerce_website.entity.OrderDetail;
import com.example.ecommerce_website.entity.ProductDetail;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.repository.OrderDetailRepository;
import com.example.ecommerce_website.repository.ProductDetailRepository;
import com.example.ecommerce_website.services.interfaces.IOrderDetailService;
import com.example.ecommerce_website.services.interfaces.IProductDetailService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Builder
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private IProductDetailService productDetailService;
    @Override
    public double getPrice(OrderDetailDtoRequest orderDetail) {
        ProductDetail productDetailFound = productDetailRepository.findById(orderDetail.getProductDetailId()).orElseThrow(
                () -> new NotFoundException("Product not found!")
        );
        double totalPrice = productDetailFound.getPrice() * orderDetail.getQuantity();
        return totalPrice;
    }

    @Override
    public List<OrderDetail> addOrderDetails(List<OrderDetailDtoRequest> orderDetailDtoRequestList, Order order) {
        String active = "Active";
        List<OrderDetail> savedOrderDetailList = new ArrayList<>();
        for (OrderDetailDtoRequest orderDetailDtoRequest : orderDetailDtoRequestList) {
            ProductDetail productDetail = productDetailRepository.findById(orderDetailDtoRequest.getProductDetailId()).get();
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .productDetail(productDetail)
                    .quantity(orderDetailDtoRequest.getQuantity())
                    .status(active)
                    .build();

            savedOrderDetailList.add(orderDetailRepository.save(orderDetail));
            productDetailService.updateStock(orderDetailDtoRequest.getProductDetailId(), orderDetailDtoRequest.getQuantity());
        }
        return savedOrderDetailList;
    }

}
