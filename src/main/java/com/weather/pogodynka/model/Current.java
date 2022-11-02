package com.weather.pogodynka.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Current {
    private double temp;
    private double pressure;
    private double humidity;
    @SerializedName("wind_speed")
    private double windSpeed;
    @SerializedName("weather")
    private List<WeatherDetails> weatherDetails;

    public Current(double temp, double pressure, double humidity, double windSpeed, List<WeatherDetails> weatherDetails) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.weatherDetails = weatherDetails;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public List<WeatherDetails> getWeatherDetails() {
        return weatherDetails;
    }

    public void setWeatherDetails(List<WeatherDetails> weatherDetails) {
        this.weatherDetails = weatherDetails;
    }
}
