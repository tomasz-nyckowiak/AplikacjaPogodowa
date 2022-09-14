package com.weather.pogodynka.controller.persistence;

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
            System.out.println("Nie znaleziono pliku!");
            return null;
        }
    }

    public void saveUserCityNameToFile(String cityName) {
        try {
            String path = "miasto.txt";
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(cityName);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCityNameFromFileValid() {
        String cityName = loadUserCityNameFromFile();
        try {
            if (cityName.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e) {
            //e.printStackTrace();
            System.out.println("Plik jest pusty!");
            return false;
        }
    }

    public void resetDefaultCityName() {
        try {
            String path = "miasto.txt";
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
