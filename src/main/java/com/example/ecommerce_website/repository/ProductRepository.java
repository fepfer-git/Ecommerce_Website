package com.example.ecommerce_website.repository;

import com.example.ecommerce_website.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

        }
