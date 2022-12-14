package com.example.ecommerce_website.repository;

import com.example.ecommerce_website.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByStatus(String status);
    List<Product> findProductsByCategory_CategoryId(int categoryId);
    List<Product> findProductsByProductNameContainingIgnoreCase(String searchKey);
    List<Product> findProductsByProductNameIgnoreCase(String productName);
}
