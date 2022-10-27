package com.example.ecommerce_website.dto.create;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SizeDto {
    private int sizeId;
    @NotBlank(message = "Size name cannot be empty!")
    private String sizeName;

}
