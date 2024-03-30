package com.evaloper.TodoApp.services;

import com.evaloper.TodoApp.dto.AuthResponse;
import com.evaloper.TodoApp.dto.LoginRequestDto;
import com.evaloper.TodoApp.dto.LoginResponse;
import com.evaloper.TodoApp.dto.RegisterRequestDto;

public interface UserService {
    AuthResponse registerUser(RegisterRequestDto registerRequestDTO);
    LoginResponse login(LoginRequestDto loginRequestDTO);

}
