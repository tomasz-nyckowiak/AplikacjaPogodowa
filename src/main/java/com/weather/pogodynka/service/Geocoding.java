package com.weather.pogodynka.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Geocoding {
    private static final String DESTINATION_URL = "http://api.openweathermap.org/geo/1.0/direct?q=";
    private static final String LIMIT = "&limit=3";
    private static final String LAST_PART = "&appid=";
    private static final String API_KEY = "68400f7feeb86d6511a8bce5d0bd3036";
    private String userInput;

    public StringBuffer getDestination() {
        StringBuffer content = new StringBuffer();
        String destination = getUserInput();
        try {
            URL url = new URL(DESTINATION_URL + destination + LIMIT + LAST_PART + API_KEY);
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

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
