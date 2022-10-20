package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.ProductDto;
import com.example.ecommerce_website.dto.update.ProductDtoUpdate;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductDto>  getProductById(@PathVariable ("productId") int id){
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity <List<Product>>  getListProductsByName(@PathVariable ("productName") String searchName){
        List<Product> products = productService.getProductByName(searchName);
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("create")
    public void createNewProduct(@RequestBody Product product){
        productService.createNewProduct(product);
    }

    @PutMapping("update")
    public ResponseEntity<Product> updateAProduct(@RequestBody ProductDtoUpdate productDtoUpdate){
        return ResponseEntity.ok().body(
                productService.updateAProduct(productDtoUpdate));
    }

    @DeleteMapping(path = "delete/{productId}")
    public void deleteAProduct(@PathVariable("productId") int id){
        productService.deleteAProduct(id);
    }



}