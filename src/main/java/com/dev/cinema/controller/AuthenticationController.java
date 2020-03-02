package com.dev.cinema.controller;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.dto.request.UserRequestDto;
import com.dev.cinema.service.AuthenticationService;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class);
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid UserRequestDto userRequestDto) {
        try {
            authenticationService.login(userRequestDto.getEmail(), userRequestDto.getPassword());
            return "Hello, " + userRequestDto.getEmail() + ".";
        } catch (AuthenticationException e) {
            LOGGER.error("Cannot login with email = " + userRequestDto.getEmail()
                    + " and password = " + userRequestDto.getPassword(), e);
            return "Please check email or password";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(),
                userRequestDto.getPassword());
        return "Registration complete";
    }
}
