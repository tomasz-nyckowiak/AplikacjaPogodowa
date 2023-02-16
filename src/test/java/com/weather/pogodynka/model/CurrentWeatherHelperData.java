package com.weather.pogodynka.model;

public class CurrentWeatherHelperData {
    private double temperature;
    private double pressure;
    private double humidity;
    private double windSpeed;
    private String description;

    public CurrentWeatherHelperData(double temperature, double pressure, double humidity, double windSpeed, String description) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
    }
}
