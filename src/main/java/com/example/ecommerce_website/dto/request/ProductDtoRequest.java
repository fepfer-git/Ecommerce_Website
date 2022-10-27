package com.example.ecommerce_website.dto.request;

import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.entity.ProductDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Product} entity
 */
@Data
public class ProductDtoRequest {
    private int productId;
    private String productName;
    private String productDescription;
    private List<ProductDetailDtoRequest> productDetails;
    private int categoryId;
}