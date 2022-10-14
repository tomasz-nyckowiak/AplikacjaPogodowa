package com.weather.pogodynka.service;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect {
    public StringBuffer setConnection(String usedURLPath, Label errorMessage) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(usedURLPath);
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
            errorMessage.setText("Wystąpił błąd!");
            errorMessage.setTextFill(Color.valueOf("#e12121"));
            StringBuffer error = new StringBuffer();
            error.append("Podano nieprawidłowy klucz!");
            content = error;
        }
        return content;
    }
}
