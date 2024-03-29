package com.evaloper.TodoApp.controller;

import com.evaloper.TodoApp.dto.AuthResponse;
import com.evaloper.TodoApp.dto.LoginRequestDto;
import com.evaloper.TodoApp.dto.LoginResponse;
import com.evaloper.TodoApp.dto.RegisterRequestDto;
import com.evaloper.TodoApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestDto registerRequestDTO){
        return ResponseEntity.ok(userService.registerUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }
}
