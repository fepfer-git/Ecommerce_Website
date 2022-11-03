package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.request.SizeDtoRequest;

import java.util.List;

public interface ISizeService {
    SizeDtoRequest createNewSize(SizeDtoRequest size);

    List<SizeDtoRequest> getAllSize();

    void deleteASize(int id);
}
