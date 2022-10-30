package com.example.ecommerce_website.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.Rating} entity
 */
@Data
public class RatingDtoResponse {
    private int ratingId;
    private UserDtoResponse user;
    private int rating;
    private String comment;
    private Date ratingDate;
}