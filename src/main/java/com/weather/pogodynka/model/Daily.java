package com.weather.pogodynka.model;

import com.google.gson.annotations.SerializedName;

public class Daily {
    @SerializedName("temp")
    private Temperature temperature;
    @SerializedName("pressure")
    private String pressure;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("wind_speed")
    private String windSpeed;
    @SerializedName("weather")
    private SecondWeather[] secondWeather;

    public Daily(Temperature temperature, String pressure, String humidity,
                 String windSpeed, SecondWeather[] secondWeather) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.secondWeather = secondWeather;
    }

    public String getTemperature() {
        return temperature.getTemperature();
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public SecondWeather[] getSecondWeather() {
        return secondWeather;
    }

    public void setSecondWeather(SecondWeather[] secondWeather) {
        this.secondWeather = secondWeather;
    }
}