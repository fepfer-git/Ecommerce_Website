package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.create.UserDto;
import com.example.ecommerce_website.dto.response.UserDtoResponse;

import java.util.List;

public interface IUserService {
    UserDtoResponse createNewUser(UserDto userDto);
    UserDtoResponse getUser(int userId);
    List<UserDtoResponse> getUsers();

}
