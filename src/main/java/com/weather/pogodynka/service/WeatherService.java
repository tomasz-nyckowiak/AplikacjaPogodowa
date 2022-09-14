package com.weather.pogodynka.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/onecall?";
    private static final String API_KEY = "68400f7feeb86d6511a8bce5d0bd3036";
    private static final String LAT = "lat=";
    private String latitude;
    private static final String LON = "&lon=";
    private String longitude;
    private String language = "&lang=pl";
    private String unitsOfMeasurement = "&units=metric";
    private static final String LAST_PART = "&exclude=minutely,hourly&appid=";


    public StringBuffer getWeather() {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(WEATHER_URL + LAT + latitude + LON + longitude + language + unitsOfMeasurement + LAST_PART + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void settingLat(String lat) {
        latitude = lat;
    }

    public void settingLon(String lon) {
        longitude = lon;
    }
}
