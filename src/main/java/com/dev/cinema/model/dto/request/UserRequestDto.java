package com.dev.cinema.model.dto.request;

import com.dev.cinema.annotations.EmailValidate;
import com.dev.cinema.annotations.PasswordValidate;

import javax.validation.constraints.NotEmpty;

@PasswordValidate
public class UserRequestDto {
    @NotEmpty
    @EmailValidate
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String repeatedPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
