package com.weather.pogodynka.service;

public class WeatherService {
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/onecall?";
    private static final String LAT = "lat=";
    private static final String LON = "&lon=";
    private static final String LANGUAGE = "&lang=pl";
    private static final String UNITS_OF_MEASUREMENT = "&units=metric";
    private static final String LAST_PART = "&exclude=minutely,hourly&appid=";

    public StringBuffer getWeather(String secretKey, String latitude, String longitude) {
        String correctPath = WEATHER_URL + LAT + latitude + LON + longitude + LANGUAGE + UNITS_OF_MEASUREMENT + LAST_PART + secretKey;
        Connect connect = new Connect();
        return connect.setConnection(correctPath);
    }

    public StringBuffer isSecretKeyValid(String secretKey) {
        String latitude = "51.94";
        String longitude = "15.51";
        //Label label = new Label();
        String exemplaryURL = WEATHER_URL + LAT + latitude + LON + longitude + LANGUAGE + UNITS_OF_MEASUREMENT + LAST_PART + secretKey;
        Connect connect = new Connect();
        return connect.setConnection(exemplaryURL);
    }
}
