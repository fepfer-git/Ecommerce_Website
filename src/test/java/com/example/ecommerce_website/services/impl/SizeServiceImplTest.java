package com.example.ecommerce_website.services.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


import com.example.ecommerce_website.dto.request.SizeDtoRequest;
import com.example.ecommerce_website.entity.ProductDetail;
import com.example.ecommerce_website.entity.Size;
import com.example.ecommerce_website.entity.User;
import com.example.ecommerce_website.exception.BadRequestException;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.ProductDetailRepository;
import com.example.ecommerce_website.repository.SizeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SizeServiceImplTest {

    private SizeRepository sizeRepository;
    private ObjectMapperUtils objectMapperUtils;
    private ProductDetailRepository productDetailRepository;
    private SizeServiceImpl sizeService;
    private Size size;

    @BeforeEach
    void beforeEach() {
        sizeRepository = mock(SizeRepository.class);
        objectMapperUtils = mock(ObjectMapperUtils.class);
        productDetailRepository = mock(ProductDetailRepository.class);
        sizeService = SizeServiceImpl.builder().sizeRepository(sizeRepository)
                .productDetailRepository(productDetailRepository)
                .objectMapperUtils(objectMapperUtils)
                .build();
        size = mock(Size.class);
    }
//    @Test
//    void createNewSize_WhenDataValid() {
//        Size savedSize = mock(Size.class);
//        SizeDtoRequest size = mock(SizeDtoRequest.class);
//        SizeDtoRequest expected = mock(SizeDtoRequest.class);
//        when(sizeRepository.findBySizeName(size.getSizeName())).thenReturn(null);
//        when(Size.builder().sizeName(size.getSizeName()).status("Active").build()).thenReturn(savedSize);
//        when(objectMapperUtils.map(sizeRepository.save(savedSize),SizeDtoRequest.class )).thenReturn(expected);
//        SizeDtoRequest result = sizeService.createNewSize(size);
//        assertThat(result, is(expected));
//    }

    @Test
    void createNewSize_WhenSizeNameIsDuplicate() {
        Size checkSize = mock(Size.class);
        SizeDtoRequest request = mock(SizeDtoRequest.class);
        DuplicatedException thrown = assertThrows(DuplicatedException.class,
                () -> {
                    when(sizeRepository.findBySizeName(request.getSizeName())).thenReturn(checkSize);
                    when(checkSize.getStatus()).thenReturn("Active");
                    sizeService.createNewSize(request);
                });
        Assertions.assertEquals("Size name is already exist!", thrown.getMessage());
    }


    @Test
    void deleteASize_WhenSizeNotExist() {
        Size deleteSize = mock(Size.class);
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> {
                    when(sizeRepository.findById(deleteSize.getSizeId())).thenReturn(Optional.empty());
                    sizeService.deleteASize(deleteSize.getSizeId());
                });
        Assertions.assertEquals("Size doesn't exist!", thrown.getMessage());
    }


    @Test
    void deleteASize_WhenStillHaveProductInSize() {
        Size deleteSize = mock(Size.class);
        List<ProductDetail> productDetailList = new ArrayList<>();
        ProductDetail productDetail = mock(ProductDetail.class);
        productDetailList.add(productDetail);
        BadRequestException thrown = assertThrows(BadRequestException.class,
                () -> {
                    when(sizeRepository.findById(deleteSize.getSizeId())).thenReturn(Optional.of(deleteSize));
                    when(productDetailRepository.findProductDetailsBySizeSizeId(deleteSize.getSizeId())).thenReturn(productDetailList);
                    sizeService.deleteASize(deleteSize.getSizeId());
                });
        Assertions.assertEquals("Still have product detail has this size. Delete fail!", thrown.getMessage());
    }
}