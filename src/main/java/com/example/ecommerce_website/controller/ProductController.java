package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.request.ProductDtoRequest;
import com.example.ecommerce_website.dto.response.ProductDtoResponse;
import com.example.ecommerce_website.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "api")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDtoResponse> getAllProducts() {
        return productService.getListProducts();
    }

    @GetMapping("/products/available")
    public List<ProductDtoResponse> getAllProductsAvailable() {
        return productService.getListProductsAvailable();
    }

    @GetMapping("/products?category={categoryId}")
    public List<ProductDtoResponse> getAllProductsByCategoryId(@PathVariable("categoryId") int categoryId) {
        return productService.getAllProductsByCategoryId(categoryId);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable("productId") int id) {
        ProductDtoResponse productDtoResponse = productService.getProductById(id);
        return ResponseEntity.ok().body(productDtoResponse);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDtoResponse>> getListProductsByName(@RequestParam String search) {
        List<ProductDtoResponse> products = productService.getProductByName(search);
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDtoResponse> createNewProduct(@Valid @RequestBody ProductDtoRequest product) {
        ProductDtoResponse createdProduct = productService.createNewProduct(product);
        return ResponseEntity.ok().body(createdProduct);
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDtoResponse> updateAProduct(@Valid @RequestBody ProductDtoRequest productDtoUpdate) {
        return ResponseEntity.status(HttpStatus.OK).body(
                productService.updateAProduct(productDtoUpdate));
    }

    @DeleteMapping(path = "product/{productId}")
    public ResponseEntity<ProductDtoResponse> deleteAProduct(@PathVariable("productId") int id) {
        return ResponseEntity.ok().body(productService.deleteAProduct(id));
    }


}