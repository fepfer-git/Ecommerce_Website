package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.create.UserDto;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDtoResponse>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDtoResponse>getUser(@PathVariable String userId){
        return ResponseEntity.ok().body(userService.getUser(userId));
    }


    @PostMapping("/save")
    public ResponseEntity<UserDtoResponse> saveUser(@Valid @RequestBody UserDto userDto){
        UserDtoResponse userDtoResponse = userService.createNewUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResponse);
    }

}
