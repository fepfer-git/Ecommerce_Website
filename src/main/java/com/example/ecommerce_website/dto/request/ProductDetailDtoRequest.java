package com.example.ecommerce_website.dto.request;

import com.example.ecommerce_website.entity.Product;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.ProductDetail} entity
 */
@Data
public class ProductDetailDtoRequest {
    private int productDetailId;
    @Min(value = 0, message = "Stock cannot be a negative number!")
    private int stock;
    @Min(value = 0, message = "Price cannot be a negative number!")
    private double price;
    @NotBlank(message = "Size cannot be empty!")
    private int sizeId;
    private Product product;
}