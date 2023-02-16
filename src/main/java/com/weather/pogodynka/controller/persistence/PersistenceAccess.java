package com.weather.pogodynka.controller.persistence;

import com.weather.pogodynka.Constants;

import java.io.*;
import java.util.Scanner;

public class PersistenceAccess {

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

    public String saveUserCityNameToFile(String cityName) {
        String message;

        try {
            FileWriter fileWriter = new FileWriter(getMyFilePath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(cityName);
            bufferedWriter.close();
            message = "success";
        } catch (IOException e) {
            message = "error";
        }
        return message;
    }

    public boolean isCityNameFromFileValid() {
        String userLocationFromFile = null;
        File file = getMyFilePath();

        if (file.isFile()) {
            userLocationFromFile = loadUserCityNameFromFile(getMyFilePath());
        }

        if (userLocationFromFile != null) {
            return userLocationFromFile.matches(Constants.PATTERN);
        } else return false;
    }

    public String resetDefaultCityName() {
        String message;

        try {
            FileWriter fileWriter = new FileWriter(getMyFilePath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            bufferedWriter.close();
            message = "success";
        } catch (IOException e) {
            message = "error";
        }
        return message;
    }

    public File getMyFilePath() {
        String fileName = "mojeMiasto.txt";
        String workingDir = System.getProperty("user.dir");
        return new File(workingDir, fileName);
    }
}
