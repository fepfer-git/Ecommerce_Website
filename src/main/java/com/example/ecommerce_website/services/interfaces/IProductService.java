package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.create.ProductDto;
import com.example.ecommerce_website.dto.response.ProductDtoResponse;

import java.util.List;

public interface IProductService{
    List<ProductDtoResponse> getListProducts();

    List<ProductDtoResponse> getListProductsAvailable();

    ProductDtoResponse createNewProduct(ProductDto product);

    ProductDtoResponse updateAProduct(ProductDto productDto);

    ProductDtoResponse deleteAProduct(int id);

    List<ProductDtoResponse> getAllProductsByCategoryId(int id);

    ProductDtoResponse getProductById(int id);

    List<ProductDtoResponse> getProductByName(String searchName);
}
