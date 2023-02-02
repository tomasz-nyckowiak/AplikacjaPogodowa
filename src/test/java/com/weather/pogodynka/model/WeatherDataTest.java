package com.weather.pogodynka.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherDataTest {

    WeatherData weatherData = new WeatherData();

    @Test
    void getTemperatureTest() {
        //given
        String result = weatherData.getTemperature("20");

        //when
        //then
        assertEquals("20\u2103", result);
    }

    @Test
    void getPressureTest() {
        //given
        String result = weatherData.getPressure("1000");

        //when
        //then
        assertEquals("1000hPa", result);
    }

    @Test
    void getHumidityTest() {
        //given
        String result = weatherData.getHumidity("35");

        //when
        //then
        assertEquals("35%", result);
    }

    @Test
    void getWindSpeedTest() {
        //given
        String result = weatherData.getWindSpeed("2.5");

        //when
        //then
        assertEquals("2.5m/s", result);
    }

    @Test
    void getCoordinatesTest() {
        //given
        String result = weatherData.getCoordinates(51.94);

        //when
        //then
        assertEquals("51.94", result);
    }
}