package com.example.ecommerce_website.repository;

import com.example.ecommerce_website.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    List <ProductDetail> findProductDetailsBySizeSizeId(int sizeId);
}