package com.ecommerce.server.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static LocalDateTime stringToDate(String date) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter p = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, p);

    }
}
