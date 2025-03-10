package com.geekzone.store.validations.releaseDate;

import com.geekzone.store.utils.DateFormatterUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReleaseDateValidator implements ConstraintValidator<ValidReleaseDate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        try {
            LocalDate releaseDate = LocalDate.parse(value, DateFormatterUtil.dateFormatter);
            return !releaseDate.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
