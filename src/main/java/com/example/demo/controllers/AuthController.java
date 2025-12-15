package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginUserRequestDto;
import com.example.demo.dto.RegisterUserRequestDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.services.AuthService;
import com.example.demo.utils.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserInfoDto>> register(
            @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        UserInfoDto user = authService.register(registerUserRequestDto);

        return ApiResponse.send(HttpStatus.CREATED, "user registered succesfully", user);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserInfoDto>> login(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        UserInfoDto user = authService.login(loginUserRequestDto);

        return ApiResponse.send(HttpStatus.OK, "user logged in succesfully", user);
    }
}
