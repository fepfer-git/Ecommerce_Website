package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.create.UserDtoCreate;
import com.example.ecommerce_website.entity.User;
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
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/save")
    public ResponseEntity saveUser(@Valid @RequestBody UserDtoCreate userDtoCreate){
        userService.saveUser(userDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body("Create new user success!");
    }

}
