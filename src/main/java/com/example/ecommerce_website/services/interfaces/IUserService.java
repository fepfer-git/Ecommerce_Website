package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.request.UserDtoRequest;
import com.example.ecommerce_website.dto.response.UserDtoResponse;

import java.util.List;

public interface IUserService {
    UserDtoResponse createNewUser(UserDtoRequest userDtoRequest);
    UserDtoResponse getUser(int userId);
    List<UserDtoResponse> getUsers();

}
