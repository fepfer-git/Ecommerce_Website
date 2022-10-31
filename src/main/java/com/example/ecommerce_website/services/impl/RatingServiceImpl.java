package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.RatingDto;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.entity.Rating;
import com.example.ecommerce_website.entity.User;
import com.example.ecommerce_website.exception.BadRequestException;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.repository.RatingRepository;
import com.example.ecommerce_website.repository.UserRepository;
import com.example.ecommerce_website.services.interfaces.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements IRatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @Override
    public RatingDto newRating(RatingDto ratingDto) {
        String active = "Active";

        Product product = productRepository.findById(ratingDto.getProductId()).orElseThrow(
                () -> new NotFoundException("Product with id: " + ratingDto.getProductId() + " cannot be found!")
        );
        User user = userRepository.findByUserName(ratingDto.getUserName());
        if (user == null){
            throw new NotFoundException("User with Username: " + ratingDto.getUserName() + " cannot be found!");
        }
        List<Rating> ratingDuplicate = ratingRepository
                .findRatingByProductProductIdAndUserUserName(ratingDto.getProductId(), ratingDto.getUserName());

        if(ratingDuplicate.size() > 0){
            throw new DuplicatedException("You are already rated this product!");
        }

        Date nowDate = new Date(System.currentTimeMillis());
        Rating newRating = Rating.builder()
                .rating(ratingDto.getRating())
                .comment(ratingDto.getComment())
                .product(product)
                .user(user)
                .status(active)
                .ratingDate(nowDate).
                build();
        Rating savedRating = ratingRepository.save(newRating);

        ratingDto.setRatingId(savedRating.getRatingId());
        ratingDto.setRatingDate(nowDate);
        return ratingDto;
    }

    @Override
    public List<RatingDto> getRatingsByProduct(int productId) {
        String active = "Active";
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product with product id: " + productId + " doesn't exist!")
        );
        List<Rating> ratingList = ratingRepository.findRatingsByProductProductIdAndStatus(product.getProductId(), active);
        return ObjectMapperUtils.mapAll(ratingList, RatingDto.class);
    }

    @Override
    public RatingDto deleteARating(int id) {
        String inactive = "Inactive";
        Rating deleteRating = ratingRepository.findById(id).get();
        if (deleteRating == null) {
            throw new NotFoundException("Rating not found!");
        } else if (inactive.equals(deleteRating.getStatus())) {
            throw new BadRequestException("Rating are already deleted!");
        }
        deleteRating.setStatus(inactive);
        ratingRepository.save(deleteRating);
        return objectMapperUtils.map(deleteRating, RatingDto.class);
    }

}
