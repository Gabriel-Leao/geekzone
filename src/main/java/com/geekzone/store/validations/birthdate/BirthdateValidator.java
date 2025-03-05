package com.geekzone.store.validations.birthdate;

import com.geekzone.store.utils.DateFormatterUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class BirthdateValidator implements ConstraintValidator<ValidBirthdate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        try {
            LocalDate birthdate = LocalDate.parse(value, DateFormatterUtil.dateFormatter);

            if (birthdate.isAfter(LocalDate.now())) {
                return false;
            }

            int age = Period.between(birthdate, LocalDate.now()).getYears();

            return age >= 18;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
