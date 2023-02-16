package com.weather.pogodynka.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.weather.pogodynka.service.Icons;

import java.util.List;
import java.util.Optional;

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

    public static String[] gettingCurrentWeatherOutput(StringBuffer content) {
        Weather weather = new Gson().fromJson(content.toString(), Weather.class);
        Current current = weather.getCurrent();
        Optional<WeatherDetails> weatherDetails = current.getWeatherDetails().stream().findFirst();
        String description = weatherDetails.map(WeatherDetails::getDescription).orElse("Brak danych");

        String temperatureAsString = String.valueOf(weather.getCurrent().getTemp());
        String temperature = WeatherData.getTemperature(temperatureAsString);

        String pressureAsString = String.valueOf(weather.getCurrent().getPressure());
        String pressure = WeatherData.getPressure(pressureAsString);

        String humidityAsString = String.valueOf(weather.getCurrent().getHumidity());
        String humidity = WeatherData.getHumidity(humidityAsString);

        String windSpeedAsString = String.valueOf(weather.getCurrent().getWindSpeed());
        String windSpeed = WeatherData.getWindSpeed(windSpeedAsString);

        String[] forecastForToday = new String[5];
        forecastForToday[0] = temperature;
        forecastForToday[1] = pressure;
        forecastForToday[2] = humidity;
        forecastForToday[3] = windSpeed;
        forecastForToday[4] = description;

        // SETTING ICON
        String iconCode = weatherDetails.map(WeatherDetails::getIcon).orElse("Domyślny kod ikony");
        Icons.settingIconCodeForTodaysWeather(iconCode);

        return forecastForToday;
    }
}
