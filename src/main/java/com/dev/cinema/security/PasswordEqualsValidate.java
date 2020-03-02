package com.dev.cinema.security;

import com.dev.cinema.annotations.PasswordValidate;
import com.dev.cinema.model.dto.request.UserRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordEqualsValidate implements ConstraintValidator<PasswordValidate,
        UserRequestDto> {
    @Override
    public void initialize(PasswordValidate constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRequestDto user,
                           ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword().equals(user.getRepeatedPassword());
    }
}
