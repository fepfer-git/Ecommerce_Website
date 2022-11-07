package com.example.ecommerce_website.repository;

import com.example.ecommerce_website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUserName(String userName);

    List<User> findUsersByRole(String role);
}
