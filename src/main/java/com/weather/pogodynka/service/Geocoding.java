package com.weather.pogodynka.service;

import javafx.scene.control.Label;

public class Geocoding {
    private static final String DESTINATION_URL = "http://api.openweathermap.org/geo/1.0/direct?q=";
    private static final String LIMIT = "&limit=3";
    private static final String LAST_PART = "&appid=";

    private Label label = new Label();

    public StringBuffer getDestination(String secretKey, String userInput) {
        String correctPath = DESTINATION_URL + userInput + LIMIT + LAST_PART + secretKey;
        Connect connect = new Connect();
        return connect.setConnection(correctPath);
    }

    public void setLabel(Label errorMessage) {
        this.label = errorMessage;
    }

    public Label getLabel() {
        return label;
    }
}
