package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.create.UserDto;
import com.example.ecommerce_website.dto.response.UserDtoResponse;
import com.example.ecommerce_website.security.JwtUtil;
import com.example.ecommerce_website.services.interfaces.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;


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

    @GetMapping("user/{userId}")
    public ResponseEntity<UserDtoResponse>getUser(@PathVariable int userId){
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @PostMapping("user/create")
    public ResponseEntity<UserDtoResponse> saveUser(@Valid @RequestBody UserDto userDto){
        UserDtoResponse userDtoResponse = userService.createNewUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResponse);
    }
}
