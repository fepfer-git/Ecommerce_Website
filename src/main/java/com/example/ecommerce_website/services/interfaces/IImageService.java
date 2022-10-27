package com.example.ecommerce_website.services.interfaces;


import com.example.ecommerce_website.entity.Image;

public interface IImageService {
    Image saveImage(Image image, int productId);
}
