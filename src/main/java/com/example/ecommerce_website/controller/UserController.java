package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.request.UserDtoRequest;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.security.JwtUtil;
import com.example.ecommerce_website.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/users")
    public ResponseEntity<List<UserDtoResponse>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("user/{userName}")
    public ResponseEntity<UserDtoResponse>getUser(@PathVariable String userName){
        return ResponseEntity.ok().body(userService.getUser(userName));
    }
    @PostMapping("user")
    public ResponseEntity<UserDtoResponse> saveUser(@Valid @RequestBody UserDtoRequest userDtoRequest){
        UserDtoResponse userDtoResponse = userService.createNewUser(userDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResponse);
    }

    @PutMapping("user")
    public ResponseEntity<UserDtoResponse> updateUserInfo(@RequestBody UserDtoRequest userDtoRequest){
        UserDtoResponse userDtoResponse = userService.updateAUser(userDtoRequest);
        return ResponseEntity.ok().body(userDtoResponse);
    }


}
