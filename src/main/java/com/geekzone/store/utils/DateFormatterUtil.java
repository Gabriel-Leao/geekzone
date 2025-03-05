package com.geekzone.store.utils;

import java.time.format.DateTimeFormatter;

public class DateFormatterUtil {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
}
