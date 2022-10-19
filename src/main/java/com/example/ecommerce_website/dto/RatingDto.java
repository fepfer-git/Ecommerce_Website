package com.example.ecommerce_website.dto;

import com.example.ecommerce_website.entity.Rating;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Rating} entity
 */
@Data
public class RatingDto {
    private int rating;
}