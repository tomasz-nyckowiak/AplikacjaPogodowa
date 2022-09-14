package com.weather.pogodynka.model;

import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("day")
    private String temperature;

    public Temperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
