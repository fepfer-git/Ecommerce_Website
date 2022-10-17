package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class ProductController {

    @GetMapping
    int getProduct() {
        return 1;
    }
}