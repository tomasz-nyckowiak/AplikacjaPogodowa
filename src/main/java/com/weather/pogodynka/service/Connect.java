package com.weather.pogodynka.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect {

    public StringBuffer setConnection(String usedURLPath) {
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
            StringBuffer error = new StringBuffer();
            error.append("Podano nieprawidłowy klucz!");
            content = error;
        }
        //System.out.println(content);
        return content;
    }
}
