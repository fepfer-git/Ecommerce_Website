package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.ProductDto;
import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.entity.Product;

import java.util.List;

public interface IProductService{
    List<Product> getListProducts();

    List<Product> getListProductsAvailable();

    void createNewProduct(Product product);

    void updateAProduct(Product product);

    void deleteAProduct(int id);

    List<Product> getAllProductsByCategoryId(int id);

    ProductDto getProductById(int id);
}
