package com.example.ecommerce_website.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Image} entity
 */
@Data
public class ImageDtoResponse {
    private int imageId;
    private String imageUrl;
}