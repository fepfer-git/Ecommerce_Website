package com.example.ecommerce_website.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Size} entity
 */
@Data
public class SizeDtoResponse{
    private int sizeId;
    private String sizeName;
}