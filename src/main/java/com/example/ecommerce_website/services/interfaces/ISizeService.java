package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.create.SizeDto;

import java.util.List;

public interface ISizeService {
    SizeDto createNewSize(SizeDto size);

    List<SizeDto> getAllSize();
}
