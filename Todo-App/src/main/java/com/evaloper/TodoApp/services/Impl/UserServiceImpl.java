package com.evaloper.TodoApp.services.Impl;

import com.evaloper.TodoApp.config.JwtService;
import com.evaloper.TodoApp.dto.*;
import com.evaloper.TodoApp.repository.UserRepository;
import com.evaloper.TodoApp.services.EmailSenderService;
import com.evaloper.TodoApp.services.UserService;
import com.evaloper.TodoApp.utils.AccountUtils;
import com.evaloper.TodoApp.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSenderService emailSenderService;
    @Override
    public AuthResponse registerUser(RegisterRequestDto registerRequestDTO) {
        User userEntity = User.builder()
                .firstName(registerRequestDTO.getFirstName())
                .lastName(registerRequestDTO.getLastName())
                .email(registerRequestDTO.getEmail())
                .password(encoder.encode(registerRequestDTO.getPassword()))
                .phoneNo(registerRequestDTO.getPhoneNo())
                .build();

        User savedUser = userRepository.save(userEntity);

        //SEND EMAIL ALERT
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("Account Creation Alert")
                .messageBody("Congratulations, your account has been created successfully!")
                .build();

        emailSenderService.sendEmailAlert(emailDetails);

        var jwtToken = jwtService.generateToken(userEntity);

        return AuthResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .registrationInfo(RegistrationInfo.builder()
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .email(userEntity.getEmail())
                        .token(jwtToken)
                        .build())
                .build();
    }
    public LoginResponse login(LoginRequestDto loginRequestDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()
                )
        );

        User userEntity = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(userEntity);

        return LoginResponse
                .builder()
                .responseCode(AccountUtils.LOGIN_SUCCESS_CODE)
                .responseMessage(AccountUtils.LOGIN_SUCCESS_MESSAGE)
                .loginInfo(LoginInfo.builder()
                        .email(userEntity.getEmail())
                        .token(jwtToken)
                        .build())
                .build();
    }

    @Override
    public User findById(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }


}
