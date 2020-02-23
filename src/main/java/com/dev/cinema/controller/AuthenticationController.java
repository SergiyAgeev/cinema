package com.dev.cinema.controller;

import com.dev.cinema.model.dto.request.UserRequestDto;
import com.dev.cinema.service.AuthenticationService;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class);
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto) {
        try {
            authenticationService.login(userRequestDto.getEmail(), userRequestDto.getPassword());
            return "Hello, " + userRequestDto.getEmail();
        } catch (javax.security.sasl.AuthenticationException e) {
            LOGGER.error("Problem with email =  " + userRequestDto.getEmail(), e);
            return "Please check your email or password";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(), userRequestDto.getPassword());
        return "Registration complete";
    }
}
