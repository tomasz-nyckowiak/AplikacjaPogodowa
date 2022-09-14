package com.weather.pogodynka.service;

public class Icons {
    private static final String URL_PATH = "http://openweathermap.org/img/wn/";
    private String iconCode;
    private static final String LAST_PART = "@2x.png";

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        String finalPath = URL_PATH + iconCode + LAST_PART;
        this.iconCode = finalPath;
    }
}