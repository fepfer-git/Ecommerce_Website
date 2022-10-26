package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.create.UserDto;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.entity.User;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ModelMapperConfiguration;
import com.example.ecommerce_website.services.details.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
class UserServiceImplTest {
    private UserRepository userRepository;
    private User user;
    private UserDto userDto;
    private User initialUser;
    private ModelMapper modelMapper;
    private UserServiceImpl userServiceImpl;
    private ModelMapperConfiguration listMapper;
    private UserDtoResponse userDtoResponse;
    private PasswordEncoder passwordEncoder;
    @Mock
    List<User> userList;
    @Mock
    List<UserDto> userDtosList;
    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        user = mock(User.class);
        userDto = mock(UserDto.class);
        initialUser = mock(User.class);
        modelMapper = mock(ModelMapper.class);
        listMapper = mock(ModelMapperConfiguration.class);
        userServiceImpl = new UserServiceImpl(listMapper, modelMapper,userRepository, passwordEncoder);
        userDtoResponse = mock(UserDtoResponse.class);
    }

    @Test
    void saveUser_WhenDataValid() {
        when(modelMapper.map(userDto,User.class)).thenReturn(user);
        when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user,UserDtoResponse.class)).thenReturn(userDtoResponse);
        UserDtoResponse userResult = userServiceImpl.createNewUser(userDto);
        assertThat(userResult,is(userDtoResponse));
    }

    @Test
    void saveUser_WhenUserIdIsDuplicated(){

        DuplicatedException thrown = assertThrows(DuplicatedException.class,
                ()->{
                    when(modelMapper.map(userDto,User.class)).thenReturn(user);
                    when(userRepository.findByUserName(userDto.getUserName())).thenReturn(user);
                    userServiceImpl.createNewUser(userDto);

                });
        Assertions.assertEquals("Username is already exist!", thrown.getMessage());

    }

    @Test
    void saveUser_WhenEmailIsDuplicated(){

        DuplicatedException thrown = assertThrows(DuplicatedException.class,
                ()->{
                    when(modelMapper.map(userDto,User.class)).thenReturn(user);
                    when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
                    when(userRepository.findByEmail(userDto.getEmail())).thenReturn(user);
                    userServiceImpl.createNewUser(userDto);
                });
        Assertions.assertEquals("Email is already exist!", thrown.getMessage());

    }


    @Test
    void getUser_ShouldReturnUser_WhenDataValid() {
        when(userRepository.findById(initialUser.getUserId())).thenReturn(Optional.of(initialUser));
        when(modelMapper.map((Optional.of(initialUser)).get(), UserDtoResponse.class)).thenReturn(userDtoResponse);
        UserDtoResponse userResult = userServiceImpl.getUser(initialUser.getUserId());
        assertThat(userResult, is(userDtoResponse));
    }

    @Test
    void getUser_ShouldReturnUser_WhenUserIdIsNotFound(){
        NotFoundException thrown = assertThrows(NotFoundException.class,
                ()->{
                    when(userRepository.findById(initialUser.getUserId())).thenReturn(Optional.empty());
                    userServiceImpl.getUser(initialUser.getUserId());
                });
        Assertions.assertEquals("There is no user with this user ID!", thrown.getMessage());
    }


//    @Test
//    void getUsers() {
//        when(userRepository.findAll()).thenReturn(userList);
//        when(listMapper.mapToUserDtoList(userList)).thenReturn(userDtosList);
//        List<UserDto> listResult = userServiceImpl.getUsers();
//        assertThat(listResult,is(userList));
//    }
}