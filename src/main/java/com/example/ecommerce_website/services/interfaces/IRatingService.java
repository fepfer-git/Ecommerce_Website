package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.RatingDto;
import com.example.ecommerce_website.dto.response.RatingDtoResponse;

import java.util.List;

public interface IRatingService {
    RatingDto newRating(RatingDto ratingDto);

    List<RatingDtoResponse> getRatingsByProduct(int productId);
    RatingDto deleteARating(int id);
}
