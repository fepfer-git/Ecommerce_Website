package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.create.UserDtoCreate;
import com.example.ecommerce_website.entity.User;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.repository.UserRepository;
import com.example.ecommerce_website.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService {

    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Override

    public void saveUser(UserDtoCreate userDtoCreate) {
        System.out.println(userDtoCreate);
        log.info("Saving new user to the database");
        User user = new User(userDtoCreate.getUserId(), userDtoCreate.getName(), userDtoCreate.getPassword() ,
                userDtoCreate.getEmail(), "Active", "USER", null, null);

        if(userRepository.findByUserId(userDtoCreate.getUserId())!= null){
            throw new DuplicatedException("UserID are already exist!");
        }else if(userRepository.findByEmail(userDtoCreate.getEmail())!= null){
            throw new DuplicatedException("Email are already exist!");
        }else{
            userRepository.save(user);
        }
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
