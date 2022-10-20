package com.example.ecommerce_website.dto.update;

import com.example.ecommerce_website.entity.Image;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Image} entity
 */
@Data
public class ImageDto implements Serializable {
    private final int imageId;
    private final String imageUrl;
}