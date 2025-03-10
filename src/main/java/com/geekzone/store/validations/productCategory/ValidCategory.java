package com.geekzone.store.validations.productCategory;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CategoryValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategory {
    String message() default "Invalid category. Allowed values are GAMES, BOOKS, MUSIC_ALBUMS, or COMICS.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
