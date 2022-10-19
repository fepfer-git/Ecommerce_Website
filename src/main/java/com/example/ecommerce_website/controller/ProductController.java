package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.ProductDto;
import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("all")
    public List<Product> getAllProducts(){
        return productService.getListProducts();
    }

    @GetMapping("all/available")
    public List<Product> getAllProductsAvailable(){
        return productService.getListProductsAvailable();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getAllProductsByCategoryId(@PathVariable ("categoryId") int id){
        return productService.getAllProductsByCategoryId(id);
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable ("productId") int id){
        return productService.getProductById(id);
    }

    @PostMapping("create")
    public void createNewProduct(@RequestBody Product product){
        productService.createNewProduct(product);
    }

    @PutMapping("update")
    public void updateAProduct(@RequestBody Product product){
        productService.updateAProduct(product);
    }

    @DeleteMapping(path = "delete/{productId}")
    public void deleteAProduct(@PathVariable("productId") int id){
        productService.deleteAProduct(id);
    }

}