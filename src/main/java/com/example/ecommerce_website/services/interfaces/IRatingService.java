package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.RatingDto;

import java.util.List;

public interface IRatingService {
    RatingDto newRating(RatingDto ratingDto);

    List<RatingDto> getRatingsByProduct(int productId);
    RatingDto deleteARating(int id);
}
