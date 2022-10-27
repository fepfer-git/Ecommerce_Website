package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.entity.Image;
import com.example.ecommerce_website.repository.ImageRepository;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.services.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Image saveImage(Image image, int productId) {
        image.setProduct(productRepository.getById(productId));
        return imageRepository.save(image);
    }
}
