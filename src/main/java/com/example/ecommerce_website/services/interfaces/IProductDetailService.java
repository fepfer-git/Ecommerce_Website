package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.request.ProductDetailDtoRequest;
import com.example.ecommerce_website.dto.response.ProductDetailDtoResponse;
import com.example.ecommerce_website.dto.response.ProductDetailDtoResponseWithProduct;
import com.example.ecommerce_website.entity.ProductDetail;

import java.util.List;

public interface IProductDetailService {
    ProductDetailDtoRequest addNewProductDetail(ProductDetailDtoRequest productDetailDtoRequest);
    ProductDetail updateProductDetail(ProductDetailDtoRequest productDetailDtoRequest);
    void updateStock(int productDetailId, int quantity);
    ProductDetailDtoResponseWithProduct getProductDetailById(int id);
}
