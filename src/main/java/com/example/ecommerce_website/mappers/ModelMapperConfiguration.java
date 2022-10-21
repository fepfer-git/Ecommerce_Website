package com.example.ecommerce_website.mappers;

import com.example.ecommerce_website.dto.create.UserDto;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public List<UserDto> mapToUserDtoList (List<User> userList){
        List<UserDto> userDtosList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = modelMapper().map(user, UserDto.class);
            userDtosList.add(userDto);
        }
        return userDtosList;
    }

    public List<User> mapToUserEntityList (List<UserDto> userDtosList){
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : userDtosList) {
            User user = modelMapper().map(userDto, User.class);
            userList.add(user);
        }
        return userList;
    }

    public List<UserDtoResponse> mapToUserDtoResponseList (List<User> userList){
        List<UserDtoResponse> userDtosList = new ArrayList<>();
        for (User user : userList) {
            UserDtoResponse userDto = modelMapper().map(user, UserDtoResponse.class);
            userDtosList.add(userDto);
        }
        return userDtosList;
    }

}
