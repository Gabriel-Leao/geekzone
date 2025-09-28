package com.geekzone.store.utils;

import com.geekzone.store.exceptions.BadRequestException;

public class EnumConverter {
    public static <E extends Enum<E>> E toEnumOrDefault(
            String value,
            Class<E> enumClass,
            E defaultValue
    ) {
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return defaultValue;
        }
    }

    public static <E extends Enum<E>> E toEnum(
            String value,
            Class<E> enumClass
    ) {
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }
}
