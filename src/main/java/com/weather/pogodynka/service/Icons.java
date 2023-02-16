package com.weather.pogodynka.service;

public class Icons {
    private static final String URL_PATH = "http://openweathermap.org/img/wn/";
    private static final String LAST_PART = "@2x.png";

    public static String[] imagesCodes = new String[5];
    public static String imageCode;

    public static String getImageCode(String iconCode) {
        return URL_PATH + iconCode + LAST_PART;
    }

    public static void settingIconsCodes(String[] iconsCodes) {
        imagesCodes = iconsCodes;
    }

    public static void settingIconCodeForTodaysWeather(String iconCode) {
        imageCode = iconCode;
    }
}