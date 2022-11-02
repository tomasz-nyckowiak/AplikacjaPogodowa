package com.weather.pogodynka.model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("current")
    private Current current;

    @SerializedName("daily")
    private Daily[] daily;

    public Weather(Current current, Daily[] daily) {
        this.current = current;
        this.daily = daily;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Daily[] getDaily() {
        return daily;
    }

    public void setDaily(Daily[] daily) {
        this.daily = daily;
    }

}


