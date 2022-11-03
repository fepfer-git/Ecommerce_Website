package com.example.ecommerce_website.repository;

import com.example.ecommerce_website.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    Size findBySizeName(String sizeName);

}
