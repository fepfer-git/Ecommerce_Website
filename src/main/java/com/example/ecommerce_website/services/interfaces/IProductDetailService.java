package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.request.ProductDetailDtoRequest;
import com.example.ecommerce_website.entity.ProductDetail;

import java.util.List;

public interface IProductDetailService {
    ProductDetailDtoRequest addNewProductDetail(ProductDetailDtoRequest productDetailDtoRequest);
    ProductDetail updateProductDetail(ProductDetailDtoRequest productDetailDtoRequest);
}
