package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.create.ProductDto;
import com.example.ecommerce_website.dto.response.ProductDtoResponse;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "api/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/all")
    public List<ProductDtoResponse> getAllProducts() {
        return productService.getListProducts();
    }

    @GetMapping("/available")
    public List<ProductDtoResponse> getAllProductsAvailable() {
        return productService.getListProductsAvailable();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDtoResponse> getAllProductsByCategoryId(@PathVariable("categoryId") int id) {
        return productService.getAllProductsByCategoryId(id);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable("productId") int id) {
        ProductDtoResponse productDtoResponse = productService.getProductById(id);
        return ResponseEntity.ok().body(productDtoResponse);
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<List<ProductDtoResponse>> getListProductsByName(@PathVariable("productName") String searchName) {
        List<ProductDtoResponse> products = productService.getProductByName(searchName);
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDtoResponse> createNewProduct(@Valid @RequestBody ProductDto product) {
        ProductDtoResponse createdProduct = productService.createNewProduct(product);
        return ResponseEntity.ok().body(createdProduct);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDtoResponse> updateAProduct(@Valid @RequestBody ProductDto productDtoUpdate) {
        return ResponseEntity.status(HttpStatus.OK).body(
                productService.updateAProduct(productDtoUpdate));
    }

    @DeleteMapping(path = "delete/{productId}")
    public ResponseEntity<ProductDtoResponse> deleteAProduct(@PathVariable("productId") int id) {
        return ResponseEntity.ok().body(productService.deleteAProduct(id));
    }


}