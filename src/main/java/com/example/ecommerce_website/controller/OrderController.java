package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.request.OrderDtoRequest;
import com.example.ecommerce_website.dto.request.ProductDtoRequest;
import com.example.ecommerce_website.dto.response.OrderDtoResponse;
import com.example.ecommerce_website.dto.response.ProductDtoResponse;
import com.example.ecommerce_website.entity.Order;
import com.example.ecommerce_website.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;


    @PostMapping()
    public ResponseEntity<OrderDtoRequest> createNewOrder(@Valid @RequestBody OrderDtoRequest orderDtoRequest) {
        OrderDtoRequest createdOrder = orderService.createNewOrder(orderDtoRequest);
        return ResponseEntity.ok().body(createdOrder);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<List<OrderDtoResponse>> getAllOrdersByUserName(@PathVariable String userName){
        return ResponseEntity.ok().body(orderService.getAllOrdersByUserName(userName));
    }


}
