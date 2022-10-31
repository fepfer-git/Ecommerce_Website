package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.RatingDto;
import com.example.ecommerce_website.dto.response.ProductDtoResponse;
import com.example.ecommerce_website.services.interfaces.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/rating")
public class RatingController {

    @Autowired
    private IRatingService ratingService;

    @PostMapping()
    public ResponseEntity<RatingDto> newRating(@RequestBody RatingDto ratingDto){
        return ResponseEntity.ok().body(ratingService.newRating(ratingDto));
    }

    @GetMapping("/{productId}")
    public List<RatingDto> getRatingsByProduct(@PathVariable("productId") int productId) {
        return ratingService.getRatingsByProduct(productId);
    }

    @DeleteMapping(path = "/{ratingId}")
    public ResponseEntity<RatingDto> deleteARating(@PathVariable("ratingId") int id) {

        return ResponseEntity.ok().body(ratingService.deleteARating(id));
    }
}
