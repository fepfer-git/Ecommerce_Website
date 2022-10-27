package com.example.ecommerce_website.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SizeDtoRequest {
    private int sizeId;
    @NotBlank(message = "Size name cannot be empty!")
    private String sizeName;

}
