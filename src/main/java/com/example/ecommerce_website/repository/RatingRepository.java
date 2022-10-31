package com.example.ecommerce_website.repository;

import com.example.ecommerce_website.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findRatingsByProductProductIdAndStatus(int productId, String status);
    List<Rating> findRatingByProductProductIdAndUserUserName(int productId, String userName);
}
