package com.example.ecommerce_website.dto.request;

import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.entity.ProductDetail;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Product} entity
 */
@Data
public class ProductDtoRequest {
    private int productId;
    @NotBlank(message = "Product name cannot be empty!")
    private String productName;
    private int categoryId;
    private String productDescription;
    private List<ProductDetailDtoRequest> productDetails;


}