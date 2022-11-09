package com.example.ecommerce_website.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeDtoRequest {
    private int sizeId;
    @NotBlank(message = "Size name cannot be empty!")
    private String sizeName;
    private String status;

}
