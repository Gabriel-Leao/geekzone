package com.geekzone.store.validations.birthdate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthdateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBirthdate {
    String message() default "Invalid birthdate format. Expected format: dd/MM/yyyy and must be 18 years or older.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
