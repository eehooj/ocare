package com.example.ocare.global.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {

    private static final DateTimeFormatter TARGET_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDateTime parseDate(String jsonDate) {
        jsonDate = jsonDate.replace("T", " ");
        jsonDate = jsonDate.replaceAll("[+-]\\d{2}:?\\d{2}$", ""); // "+00:00" 또는 "+0000" 제거

        // "yyyy-MM-dd HH:mm:ss" 변환
        return LocalDateTime.parse(jsonDate, TARGET_FORMAT);
    }
}
