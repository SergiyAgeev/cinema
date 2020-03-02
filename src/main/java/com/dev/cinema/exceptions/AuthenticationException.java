package com.dev.cinema.exceptions;

public class AuthenticationException extends Exception {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException() {
        super("email or password is invalid");
    }
}
