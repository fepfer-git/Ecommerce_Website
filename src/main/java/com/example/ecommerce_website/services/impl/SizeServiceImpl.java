package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.SizeDtoRequest;
import com.example.ecommerce_website.entity.Size;
import com.example.ecommerce_website.exception.BadRequestException;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.ProductDetailRepository;
import com.example.ecommerce_website.repository.SizeRepository;
import com.example.ecommerce_website.services.interfaces.ISizeService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Builder
@Service

public class SizeServiceImpl implements ISizeService {
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public SizeDtoRequest createNewSize(SizeDtoRequest size) {
        Size checkSize = sizeRepository.findBySizeName(size.getSizeName());
        Size savedSize;
        if(checkSize != null && checkSize.getStatus().equals("Active")){
            throw new DuplicatedException("Size name is already exist!");
        }
        if(checkSize != null && !checkSize.getStatus().equals("Active") ){
            checkSize.setStatus("Active");
            savedSize = checkSize;
        }else{
            savedSize = Size.builder().sizeName(size.getSizeName()).status("Active").build();
        }
        return objectMapperUtils.map(sizeRepository.save(savedSize), SizeDtoRequest.class);
    }

    @Override
    public List<SizeDtoRequest> getAllSize() {
        List<Size> sizeList = sizeRepository.findSizeByStatus("Active");
        return objectMapperUtils.mapAll(sizeList, SizeDtoRequest.class);
    }

    @Override
    public void deleteASize(int id) {
        Optional<Size> deletedSize = sizeRepository.findById(id);
        if(deletedSize.isEmpty()){
            throw new NotFoundException("Size doesn't exist!");
        }
        if(productDetailRepository.findProductDetailsBySizeSizeId(id).size() > 0 ){
            throw new BadRequestException("Still have product detail has this size. Delete fail!");
        }
        deletedSize.get().setStatus("Inactive");
        sizeRepository.save(deletedSize.get());
    }
}
