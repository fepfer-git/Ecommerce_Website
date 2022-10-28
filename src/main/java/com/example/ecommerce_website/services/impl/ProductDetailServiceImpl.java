package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.ProductDetailDtoRequest;
import com.example.ecommerce_website.dto.request.ProductDtoRequest;
import com.example.ecommerce_website.entity.ProductDetail;
import com.example.ecommerce_website.entity.Size;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.ProductDetailRepository;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.repository.SizeRepository;
import com.example.ecommerce_website.services.interfaces.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements IProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Override
    public ProductDetailDtoRequest addNewProductDetail(ProductDetailDtoRequest productDetailDtoRequest) {
        Size size = sizeRepository.findById(productDetailDtoRequest.getSizeId()).get();
        if (size == null) {
            throw new NotFoundException("Size doesn't exist!");
        }
        ProductDetail productDetailCreate = ProductDetail.builder()
                .product(productDetailDtoRequest.getProduct())
                .price(productDetailDtoRequest.getPrice())
                .stock(productDetailDtoRequest.getStock())
                .status("Active")
                .size(size).build();
        return objectMapperUtils.map(productDetailRepository.save(productDetailCreate),ProductDetailDtoRequest.class);
    }

    @Override
    public ProductDetail updateProductDetail(ProductDetailDtoRequest productDetailDtoRequest){
        ProductDetail productDetailUpdate = productDetailRepository.findById(productDetailDtoRequest.getProductDetailId()).get();
        if(productDetailUpdate == null){
            throw new NotFoundException("Product detail is not found!");
        }

        if(productDetailDtoRequest.getSizeId() != productDetailUpdate.getSize().getSizeId()){
            Size newSize = sizeRepository.findById(productDetailDtoRequest.getSizeId()).get();
            productDetailUpdate.setSize(newSize);
        }
        productDetailUpdate.setStock(productDetailDtoRequest.getStock());
        productDetailUpdate.setPrice(productDetailDtoRequest.getPrice());

        return productDetailRepository.save(productDetailUpdate);
    }

}
