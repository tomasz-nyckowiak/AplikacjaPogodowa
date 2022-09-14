package com.weather.pogodynka.model;

public class WeatherData {
    // FORMATTING WEATHER DATA FOR PROPER SHOWING
    public String getTemperature(String str) {
        double val = Double.parseDouble(str);
        int x = (int) Math.round(val);
        String temp = String.valueOf(x);
        String temperature = temp + "\u2103";
        return temperature;
    }

    public String getPressure(String str) {
        double val = Double.parseDouble(str);
        int x = (int) val;
        String temp = String.valueOf(x);
        String pressure = temp + "hPa";
        return pressure;
    }

    public String getHumidity(String str) {
        double val = Double.parseDouble(str);
        int x = (int) val;
        String temp = String.valueOf(x);
        String humidity = temp + "%";
        return humidity;
    }

    public String getWindSpeed(String str) {
        double val = Double.parseDouble(str);
        double x = Math.round(val * 10.0) / 10.0;
        String temp = String.valueOf(x);
        String windSpeed = temp + "m/s";
        return windSpeed;
    }

    public String getCoordinates(double d) {
        double x = Math.round(d * 100.0) / 100.0;
        String temp = String.valueOf(x);
        return temp;
    }
}
