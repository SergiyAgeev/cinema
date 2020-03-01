package com.dev.cinema.annotations;

import com.dev.cinema.security.PasswordEqualsValidate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordEqualsValidate.class)
public @interface PasswordValidate {
    String message() default "Passwords not same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
