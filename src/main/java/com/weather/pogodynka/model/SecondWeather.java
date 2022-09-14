package com.weather.pogodynka.model;

import com.google.gson.annotations.SerializedName;

public class SecondWeather {
    @SerializedName("description")
    private String description;

    @SerializedName("icon")
    private String icon;

    public SecondWeather(String description, String icon) {
        this.description = description;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
