package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.response.ProductDetailDtoResponse;
import com.example.ecommerce_website.dto.response.ProductDtoResponse;
import com.example.ecommerce_website.entity.ProductDetail;
import com.example.ecommerce_website.services.interfaces.IProductDetailService;
import com.example.ecommerce_website.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/productDetail")
public class ProductDetailController {

    @Autowired
    private IProductDetailService productDetailService;

    @GetMapping("/{productDetailId}")
    public ResponseEntity<ProductDetailDtoResponse> getProductDetailById(@PathVariable("productDetailId") int id) {
        ProductDetailDtoResponse productDetailDtoResponse = productDetailService.getProductDetailById(id);
        return ResponseEntity.ok().body(productDetailDtoResponse);
    }

}
