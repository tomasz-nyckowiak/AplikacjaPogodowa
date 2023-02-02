package com.weather.pogodynka.controller.persistence;

import com.weather.pogodynka.Constants;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.Scanner;

public class PersistenceAccess {

    private Label label = new Label();

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String loadUserCityNameFromFile(File filePath) {
        try {
            Scanner scanner = new Scanner(filePath);
            String cityName = null;
            while (scanner.hasNextLine()) {
                cityName = scanner.nextLine();
            }
            return cityName;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void saveUserCityNameToFile(String cityName) {
        try {
            FileWriter fileWriter = new FileWriter(getMyFilePath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(cityName);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Label errorMessage = getLabel();
            errorMessage.setText("Wystąpił błąd! Spróbuj później!");
            errorMessage.setTextFill(Color.valueOf("#e12121"));
        }
    }

    public boolean isCityNameFromFileValid() {
        boolean noFile = true;
        String userLocationFromFile = null;

        File file = getMyFilePath();
        if (file.isFile()) {
            noFile = false;
            userLocationFromFile = loadUserCityNameFromFile(getMyFilePath());
        }

        if (userLocationFromFile != null) {
            if (!userLocationFromFile.matches(Constants.PATTERN)) {
                return false;
            } else {
                return true;
            }
        } else return false;
    }

    public void resetDefaultCityName() {

        Label errorMessage = getLabel();

        try {
            FileWriter fileWriter = new FileWriter(getMyFilePath());
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

    public File getMyFilePath() {
        String fileName = "mojeMiasto.txt";
        String workingDir = System.getProperty("user.dir");
        return new File(workingDir, fileName);
    }
}
