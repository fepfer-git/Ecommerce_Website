package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.create.UserDto;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.entity.User;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ModelMapperConfiguration;
import com.example.ecommerce_website.repository.UserRepository;
import com.example.ecommerce_website.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    ModelMapperConfiguration listMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override

    public UserDtoResponse createNewUser(UserDto userDto) {
        log.info("Saving new user to the database");
        User user = modelMapper.map(userDto, User.class);
        user.setStatus("Active");
        user.setRole("USER");

        if (userRepository.findById(userDto.getUserId()).isPresent()) {
            throw new DuplicatedException("UserID is already exist!");
        } else if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new DuplicatedException("Email is already exist!");
        }
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDtoResponse.class);
    }

    @Override
    public UserDtoResponse getUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            UserDtoResponse userDtoResponse = modelMapper.map(user.get(), UserDtoResponse.class);
            return userDtoResponse;
        }else{
            throw new NotFoundException("There is no user with this user ID!");
        }
    }

    @Override
    public List<UserDtoResponse> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new NotFoundException("There is nothing here!");
        }
        List<UserDtoResponse> userDtoResponseList = listMapper.mapToUserDtoResponseList(users);
        return userDtoResponseList;
    }

}
