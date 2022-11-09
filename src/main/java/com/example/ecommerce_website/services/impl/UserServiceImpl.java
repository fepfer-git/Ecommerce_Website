package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.UserDtoRequest;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.entity.User;
import com.example.ecommerce_website.exception.BadRequestException;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.UserRepository;
import com.example.ecommerce_website.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService{
    @Autowired
    ObjectMapperUtils objectMapperUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDtoResponse createNewUser(UserDtoRequest userDtoRequest) {
        log.info("Saving new user to the database");
        User user = objectMapperUtils.map(userDtoRequest, User.class);
        user.setStatus("Active");
        user.setRole("USER");
        if (userRepository.findByUserName(userDtoRequest.getUserName()) != null) {
            throw new DuplicatedException("Username is already exist!");
        } else if (userRepository.findByEmail(userDtoRequest.getEmail()) != null) {
            throw new DuplicatedException("Email is already exist!");
        }else if(!userDtoRequest.getPassword().equals(userDtoRequest.getConfirmPassword())){
            throw new BadRequestException("Confirm password is incorrect!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return objectMapperUtils.map(savedUser, UserDtoResponse.class);
    }

    @Override
    public UserDtoResponse getUser(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            UserDtoResponse userDtoResponse = objectMapperUtils.map(user, UserDtoResponse.class);
            return userDtoResponse;
        } else {
            throw new NotFoundException("There is no user with this user ID!");
        }
    }

    @Override
    public List<UserDtoResponse> getUsers() {
        List<User> users = userRepository.findUsersByRole("USER");
        if (users.isEmpty()) {
            List<UserDtoResponse> userEmptyList = new ArrayList<>();
            return userEmptyList;
        }else{
            List<UserDtoResponse> userDtoResponseList = objectMapperUtils.mapAll(users,UserDtoResponse.class);
            return userDtoResponseList;
        }

    }

    @Override
    public UserDtoResponse updateAUser(UserDtoRequest userDtoRequest) {
        User foundUser = userRepository.findByUserName(userDtoRequest.getUserName());

        if (foundUser == null){
            throw new NotFoundException("User not found!");
        }
        if (userDtoRequest.getFullName() != null){
            foundUser.setFullName(userDtoRequest.getFullName());
        }
        if(userDtoRequest.getEmail() != null){
            User checkEmailUser = userRepository.findByEmail(userDtoRequest.getEmail());
            if(checkEmailUser != null && checkEmailUser.getUserName().equals(userDtoRequest.getUserName())){
                foundUser.setEmail(userDtoRequest.getEmail());
            }else if(checkEmailUser != null){
                throw new BadRequestException("Email is already exist!");
            }else{
                foundUser.setEmail(userDtoRequest.getEmail());
            }
        }
        //Handel password
        if(null != userDtoRequest.getPassword()){
            if(!userDtoRequest.getPassword().equals(userDtoRequest.getConfirmPassword())) {
                throw new BadRequestException("Confirm password do not match!");
            }else{
                foundUser.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));
            }
        }
        return objectMapperUtils.map(userRepository.save(foundUser), UserDtoResponse.class);
    }

}
