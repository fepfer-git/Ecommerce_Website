package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.request.ProductDtoRequest;
import com.example.ecommerce_website.dto.response.ProductDtoResponse;

import java.util.List;

public interface IProductService{
    List<ProductDtoResponse> getListProducts();

    List<ProductDtoResponse> getListProductsAvailable();

    ProductDtoResponse createNewProduct(ProductDtoRequest product);

    ProductDtoResponse updateAProduct(ProductDtoRequest productDto);

    ProductDtoResponse deleteAProduct(int id);

    List<ProductDtoResponse> getAllProductsByCategoryId(int id);

    ProductDtoResponse getProductById(int id);

    List<ProductDtoResponse> getProductByName(String searchName);
}
