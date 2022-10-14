package com.weather.pogodynka.controller.persistence;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.Scanner;

public class PersistenceAccess {
    public String loadUserCityNameFromFile() {
        try {
            File file = new File("miasto.txt");
            Scanner scanner = new Scanner(file);
            String cityName = null;
            while (scanner.hasNextLine()) {
                cityName = scanner.nextLine();
            }
            return cityName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveUserCityNameToFile(String cityName, Label errorMessage) {
        try {
            String path = "miasto.txt";
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(cityName);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText("Wystąpił błąd! Spróbuj później!");
            errorMessage.setTextFill(Color.valueOf("#e12121"));
        }
    }

    public boolean isCityNameFromFileValid() {
        String pattern = "^([a-zA-Z\u0080-\u024F]+(?:(\\.) |-| |'))*[a-zA-Z\u0080-\u024F]*$";
        String userLocationFromFile = loadUserCityNameFromFile();

        if (userLocationFromFile != null) {
            if (!userLocationFromFile.matches(pattern)) {
                return false;
            } else {
                return true;
            }
        } else return false;
    }

    public void resetDefaultCityName(Label errorMessage) {
        try {
            String path = "miasto.txt";
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            bufferedWriter.close();
            errorMessage.setText("Usunięto domyślne miasto!");
            errorMessage.setTextFill(Color.GREEN);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText("Wystąpił błąd! Spróbuj jeszcze raz!");
            errorMessage.setTextFill(Color.valueOf("#e12121"));
        }
    }
}
