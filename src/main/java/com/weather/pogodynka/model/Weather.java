package com.weather.pogodynka.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.weather.pogodynka.service.Icons;

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

    public static String[] gettingWeatherOutput(StringBuffer content) {
        Weather weather = new Gson().fromJson(content.toString(), Weather.class);

        // WEATHER FORECAST DATA FOR NEXT 5 DAYS (DAILY)
        String[] forecastDay = new String[5];
        String[] temperatureDay = new String[5];
        String[] pressureDay = new String[5];
        String[] humidityDay = new String[5];
        String[] windSpeedDay = new String[5];
        String[] descriptionDay = new String[5];
        String[] iconsCodes = new String[5];

        int NUMBER_OF_DAYS_FOR_FORECAST = 5;
        for (int i = 0; i < NUMBER_OF_DAYS_FOR_FORECAST; i++) {
            temperatureDay[i] = weather.getDaily()[i + 1].getTemperature();
            pressureDay[i] = weather.getDaily()[i + 1].getPressure();
            humidityDay[i] = weather.getDaily()[i + 1].getHumidity();
            windSpeedDay[i] = weather.getDaily()[i + 1].getWindSpeed();
            descriptionDay[i] = weather.getDaily()[i + 1].getSecondWeather()[0].getDescription();
            forecastDay[i] = WeatherData.getTemperature(temperatureDay[i]) + ", " + WeatherData.getPressure(pressureDay[i]) + ", " +
                    WeatherData.getHumidity(humidityDay[i]) + ", " + WeatherData.getWindSpeed(windSpeedDay[i]) + ", " + descriptionDay[i];
            iconsCodes[i] = weather.getDaily()[i + 1].getSecondWeather()[0].getIcon();
        }

        // SETTING ICONS
        Icons.settingIconsCodes(iconsCodes);

        return forecastDay;
    }
}


