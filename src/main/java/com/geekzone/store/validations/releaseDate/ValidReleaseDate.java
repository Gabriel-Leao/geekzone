package com.geekzone.store.validations.releaseDate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ReleaseDateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidReleaseDate {
    String message() default "Invalid release date. Expected format: yyyy-MM-dd and must not be in the future.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
