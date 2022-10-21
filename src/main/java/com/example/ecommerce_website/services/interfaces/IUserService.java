package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.create.UserDto;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.entity.User;

import java.util.List;

public interface IUserService {
    UserDtoResponse saveUser(UserDto userDto);
    UserDtoResponse getUser(String userId);
    List<UserDtoResponse> getUsers();

}
