package com.example.ecommerce_website.services.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.ecommerce_website.dto.RatingDto;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.entity.Rating;
import com.example.ecommerce_website.entity.Size;
import com.example.ecommerce_website.entity.User;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RatingServiceImplTest {


    private RatingRepository ratingRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private ObjectMapperUtils objectMapperUtils;
    private RatingServiceImpl ratingService;

    private RatingDto ratingDto;
    private Product product;
    private User user;
    private Rating rating;


    @BeforeEach
    void beforeEach() {
        ratingRepository = mock(RatingRepository.class);
        objectMapperUtils = mock(ObjectMapperUtils.class);
        userRepository = mock(UserRepository.class);
        productRepository = mock(ProductRepository.class);
        ratingService = RatingServiceImpl.builder()
                .ratingRepository(ratingRepository)
                .productRepository(productRepository)
                .userRepository(userRepository)
                .objectMapperUtils(objectMapperUtils)
                .build();
        rating = mock(Rating.class);
        user = mock(User.class);
        rating = mock(Rating.class);
        ratingDto = mock(RatingDto.class);
        product = mock(Product.class);

    }


    @Test
    void newRating_WhenProductNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> {
                    when(productRepository.findById(ratingDto.getProductId())).thenReturn(Optional.empty());
                    ratingService.newRating(ratingDto);
                });
        Assertions.assertEquals("Product with id: " + ratingDto.getProductId() + " cannot be found!", thrown.getMessage());
    }

    @Test
    void newRating_WhenUserNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> {
                    when(productRepository.findById(ratingDto.getProductId())).thenReturn(Optional.of(product));
                    when(userRepository.findByUserName(ratingDto.getUserName())).thenReturn(null);
                    ratingService.newRating(ratingDto);
                });
        Assertions.assertEquals("User with Username: " + ratingDto.getUserName() + " cannot be found!", thrown.getMessage());
    }

    @Test
    void newRating_WhenUserAlreadyRated() {
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        DuplicatedException thrown = assertThrows(DuplicatedException.class,
                () -> {
                    when(productRepository.findById(ratingDto.getProductId())).thenReturn(Optional.of(product));
                    when(userRepository.findByUserName(ratingDto.getUserName())).thenReturn(user);
                    when(ratingRepository
                            .findRatingByProductProductIdAndUserUserName(ratingDto.getProductId(), ratingDto.getUserName())).thenReturn(ratingList);
                    ratingService.newRating(ratingDto);
                });
        Assertions.assertEquals("You are already rated this product!", thrown.getMessage());
    }


    @Test
    void getRatingsByProduct_WhenProductNotFound() {

        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> {
                    when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
                    ratingService.getRatingsByProduct(product.getProductId());
                });
        Assertions.assertEquals("Product with product id: " + product.getProductId() + " doesn't exist!", thrown.getMessage());
    }
}