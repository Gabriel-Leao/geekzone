package com.geekzone.store.validations.productCategory;

import com.geekzone.store.models.Categories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {

    private static final Set<String> VALID_CATEGORIES = Stream.of(Categories.values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        return VALID_CATEGORIES.contains(value.toUpperCase());
    }
}
