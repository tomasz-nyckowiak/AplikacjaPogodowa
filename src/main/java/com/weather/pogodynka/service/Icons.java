package com.weather.pogodynka.service;

public class Icons {
    private static final String URL_PATH = "http://openweathermap.org/img/wn/";
    private static final String LAST_PART = "@2x.png";

    public String getImageCode(String iconCode) {
        return URL_PATH + iconCode + LAST_PART;
    }
}