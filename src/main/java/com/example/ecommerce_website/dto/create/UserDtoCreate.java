package com.example.ecommerce_website.dto.create;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.User} entity
 */
@Data
public class UserDtoCreate {
    @NotBlank(message = "User Id cannot be blank!")
    private String userId;
    @NotBlank(message = "Name cannot be blank!")
    private String name;
    @NotBlank(message = "Password cannot be blank!")
    private String password;
    @Email(message = "Email is not valid!")
    @NotBlank(message = "Email cannot be blank!")
    private String email;
}