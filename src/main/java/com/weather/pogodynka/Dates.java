package com.weather.pogodynka;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {
    private final String[] days = new String[5];

    public String[] getForecastDays() {
        LocalDate currentLocalDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int NUMBER_OF_DAYS_FOR_FORECAST = 5;
        for (int i = 0; i < NUMBER_OF_DAYS_FOR_FORECAST; i++) {
            LocalDate nextDay = currentLocalDate.plusDays(i+1);
            String nextDayAsString = nextDay.format(dateFormat);
            days[i] = nextDayAsString;
        }
        return days;
    }

    public String setTodayDate() {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter todayDate = DateTimeFormatter.ofPattern("EEEE, dd MMMM, HH:mm");
        return currentLocalDateTime.format(todayDate);
    }
}