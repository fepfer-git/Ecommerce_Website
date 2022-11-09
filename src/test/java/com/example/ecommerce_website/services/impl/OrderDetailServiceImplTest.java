package com.example.ecommerce_website.services.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.ecommerce_website.dto.request.OrderDetailDtoRequest;
import com.example.ecommerce_website.entity.OrderDetail;
import com.example.ecommerce_website.entity.ProductDetail;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.repository.OrderDetailRepository;
import com.example.ecommerce_website.repository.ProductDetailRepository;
import com.example.ecommerce_website.services.interfaces.IProductDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;


class OrderDetailServiceImplTest {

    private ProductDetailRepository productDetailRepository;
    private OrderDetailRepository orderDetailRepository;
    private IProductDetailService productDetailService;
    private OrderDetailServiceImpl orderDetailService;
    private OrderDetail orderDetail;
    private ProductDetail productDetail;
    private OrderDetailDtoRequest orderDetailDtoRequest;
    @BeforeEach
    void beforeEach() {
        productDetailRepository = mock(ProductDetailRepository.class);
        orderDetailRepository = mock(OrderDetailRepository.class);
        productDetailRepository = mock(ProductDetailRepository.class);
        orderDetailService = OrderDetailServiceImpl.builder().orderDetailRepository(orderDetailRepository)
                .productDetailRepository(productDetailRepository)
                .productDetailService(productDetailService)
                .build();
        orderDetail = mock(OrderDetail.class);
        productDetail = mock(ProductDetail.class);
        orderDetailDtoRequest = mock(OrderDetailDtoRequest.class);
    }

    @Test
    void getPrice_WhenProductNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> {
                    when(productDetailRepository.findById(orderDetailDtoRequest.getProductDetailId())).thenReturn(Optional.empty());
                    orderDetailService.getPrice(orderDetailDtoRequest);
                });
        Assertions.assertEquals("Product not found!", thrown.getMessage());
    }
}