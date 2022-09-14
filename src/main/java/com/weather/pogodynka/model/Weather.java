package com.weather.pogodynka.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Weather {
    @SerializedName("current")
    private Map<String, Object> current;

    @SerializedName("daily")
    private Daily[] daily;

    public Weather(Map<String, Object> current, Daily[] daily) {
        this.current = current;
        this.daily = daily;
    }

    public Map<String, Object> getCurrent() {
        return current;
    }

    public void setCurrent(Map<String, Object> current) {
        this.current = current;
    }

    public Daily[] getDaily() {
        return daily;
    }

    public void setDaily(Daily[] daily) {
        this.daily = daily;
    }

}


