package com.example.ecommerce_website.services.interfaces;

import com.example.ecommerce_website.dto.create.UserDtoCreate;
import com.example.ecommerce_website.entity.User;

import java.util.List;

public interface IUserService {
    void saveUser(UserDtoCreate userDtoCreate);
    User getUser(String userId);
    List<User> getUsers();

}
