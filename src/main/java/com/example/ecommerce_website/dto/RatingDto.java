package com.example.ecommerce_website.dto;

import com.example.ecommerce_website.entity.Rating;
import lombok.Data;

import java.util.Date;

/**
 * A DTO for the {@link Rating} entity
 */
@Data
public class RatingDto {
    private int ratingId;
    private int productId;
    private int userId;
    private int rating;
    private String comment;
    private Date ratingDate;
}