package com.example.ecommerce_website.dto.create;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * A DTO for the {@link com.example.ecommerce_website.entity.User} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Length(min = 3, max = 15, message = "Username must be between 3-15 characters!")
    @NotBlank(message = "Username cannot be empty!")
    private String userName;
    @NotBlank(message = "Name cannot be empty!")
    private String fullName;
    @Length(min = 6, max = 20, message = "Password must be between 6-20 characters!")
    @NotBlank(message = "Password cannot be empty!")
    private String password;
    @Email(message = "Email is not valid!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;
}