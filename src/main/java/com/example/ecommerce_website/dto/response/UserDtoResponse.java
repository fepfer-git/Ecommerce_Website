package com.example.ecommerce_website.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.User} entity
 */
@Data
public class UserDtoResponse{
    private String userId;
    private String name;
    private String email;
    private String role;
}