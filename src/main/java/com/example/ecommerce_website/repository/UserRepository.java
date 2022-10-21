package com.example.ecommerce_website.repository;

import com.example.ecommerce_website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    User findByEmail(String email);
}
