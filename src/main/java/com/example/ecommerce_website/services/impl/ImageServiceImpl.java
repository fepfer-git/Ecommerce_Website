package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.entity.Image;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.repository.ImageRepository;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.services.interfaces.IImageService;
import com.example.ecommerce_website.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Image saveImage(Image image, int productId) {
        if(productRepository.findById(productId).isEmpty()){
            throw new NotFoundException("Product with id: "+productId+ " not found!");
        }
        image.setProduct(productRepository.findById(productId).get());
        return imageRepository.save(image);
    }
}
