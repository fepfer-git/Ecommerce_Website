package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.entity.Image;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.repository.ImageRepository;
import com.example.ecommerce_website.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.ecommerce_website.dto.request.UserDtoRequest;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.entity.User;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class ImageServiceImplTest {
    private ImageRepository imageRepository;
    private ProductRepository productRepository;
    private ImageServiceImpl imageService;
    private Image image;
    private Product product;
    int productId;

    @BeforeEach
    void setUp(){
        imageRepository = mock(ImageRepository.class);
        productRepository = mock(ProductRepository.class);
        imageService = new ImageServiceImpl(imageRepository, productRepository);
        image = mock(Image.class);
        product = mock(Product.class);
    }

    @Test
    void saveImage_WhenProductNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> {
                    when(productRepository.findById(productId)).thenReturn(Optional.empty());
                    imageService.saveImage(image, productId);
                });
        Assertions.assertEquals("Product with id: "+productId+ " not found!", thrown.getMessage());
    }

}