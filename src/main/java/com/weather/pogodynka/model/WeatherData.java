package com.weather.pogodynka.model;

public class WeatherData {
    // FORMATTING WEATHER DATA FOR PROPER SHOWING
    public static String getTemperature(String str) {
        double val = Double.parseDouble(str);
        int x = (int) Math.round(val);
        String temp = String.valueOf(x);
        return temp + "\u2103";
    }

    public static String getPressure(String str) {
        double val = Double.parseDouble(str);
        int x = (int) val;
        String temp = String.valueOf(x);
        return temp + "hPa";
    }

    public static String getHumidity(String str) {
        double val = Double.parseDouble(str);
        int x = (int) val;
        String temp = String.valueOf(x);
        return temp + "%";
    }

    public static String getWindSpeed(String str) {
        double val = Double.parseDouble(str);
        double x = Math.round(val * 10.0) / 10.0;
        String temp = String.valueOf(x);
        return temp + "m/s";
    }

    public static String getCoordinates(double d) {
        double x = Math.round(d * 100.0) / 100.0;
        return String.valueOf(x);
    }
}