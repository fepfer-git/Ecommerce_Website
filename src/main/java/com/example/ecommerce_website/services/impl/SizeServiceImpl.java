package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.SizeDtoRequest;
import com.example.ecommerce_website.entity.Size;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.SizeRepository;
import com.example.ecommerce_website.services.interfaces.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements ISizeService {
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Override
    public SizeDtoRequest createNewSize(SizeDtoRequest size) {
        Size checkSize = sizeRepository.findBySizeName(size.getSizeName());
        if(checkSize != null && "Inactive" != checkSize.getStatus()){
            throw new DuplicatedException("Size name is already exist!");
        }
        Size savedSize = Size.builder().sizeName(size.getSizeName()).status("Active").build();
        return objectMapperUtils.map(sizeRepository.save(savedSize), SizeDtoRequest.class);
    }

    @Override
    public List<SizeDtoRequest> getAllSize() {
        return objectMapperUtils.mapAll(sizeRepository.findAll(), SizeDtoRequest.class);
    }
}
