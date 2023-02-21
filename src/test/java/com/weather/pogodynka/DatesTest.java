package com.weather.pogodynka;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DatesTest {

    Dates dates = new Dates();

    @Test
    void getForecastDaysTest() {

        String[] days = new String[5];

        //given
        LocalDate currentLocalDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //when
        int NUMBER_OF_DAYS_FOR_FORECAST = 5;

        for (int i = 0; i < NUMBER_OF_DAYS_FOR_FORECAST; i++) {
            LocalDate nextDay = currentLocalDate.plusDays(i+1);
            String nextDayAsString = nextDay.format(dateFormat);
            days[i] = nextDayAsString;
        }

        String[] forecastDays = dates.getForecastDays();

        //then
        assertThat(forecastDays[0]).isEqualTo(days[0]);
        assertThat(forecastDays[4]).isEqualTo(days[4]);
        assertThat(Arrays.equals(forecastDays, days)).isTrue();
    }

    @Test
    void setTodayDateTest() {

        //given
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter todayDate = DateTimeFormatter.ofPattern("EEEE, dd MMMM, HH:mm");
        String myLocalDate = currentLocalDateTime.format(todayDate);

        //when
        String localDate = dates.setTodayDate();

        //then
        assertThat(myLocalDate.equals(localDate)).isTrue();
    }
}