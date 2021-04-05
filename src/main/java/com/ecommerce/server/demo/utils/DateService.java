package com.ecommerce.server.demo.utils;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;

public class DateService {
    public static Date parse(String date) {
        try {
            return Date.valueOf(LocalDate.parse((date)));
        } catch (DateTimeException ex) {
//            throw new InvalidDateException(date, ex);
            return null;
        }
    }
}
