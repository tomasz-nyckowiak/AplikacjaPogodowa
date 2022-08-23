package com.weather.pogodynka.controller;

import com.google.gson.Gson;
import com.weather.pogodynka.model.Weather;
import com.weather.pogodynka.service.WeatherService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class MainWindowController {

    @FXML
    private TextField chosenLocation;

    @FXML
    private TextField defaultLocation;

    @FXML
    private TextField todayDate;

    @FXML
    private Label currentTemp;
    @FXML
    private Label currentPressure;
    @FXML
    private Label currentHumidity;
    @FXML
    private Label currentWindSpeed;
    @FXML
    private Label currentDescription;

    @FXML
    private Label day1;

    @FXML
    private Label day2;

    @FXML
    private Label day3;

    @FXML
    private Label day4;

    @FXML
    private Label day5;

    private WeatherService weatherService = new WeatherService();

    @FXML
    void startButtonAction() {
        System.out.println("Start button clicked!");

        TextField textField = new TextField("Zielona Góra");
        String mojtekst = textField.textProperty().getValue();
        defaultLocation.setText(mojtekst);

        LocalDate obj = LocalDate.now();
        String dzisiaj = obj.toString();
        todayDate.setText(dzisiaj);

        // Gridpane
        // DATES
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //calendar.add(Calendar.DAY_OF_MONTH, +1);
        //String nowaData = dateFormat.format(calendar.getTime());
        //day1.setText(nowaData);

        String[] days = new String[5];

        for (int i = 0; i <= 4; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            String kolejnaData = dateFormat.format(calendar.getTime());

            days[i] = kolejnaData;
            //System.out.println(days[i]);
        }
        day1.setText(days[0]);
        day2.setText(days[1]);
        day3.setText(days[2]);
        day4.setText(days[3]);
        day5.setText(days[4]);

        // Mapowanie
        StringBuffer content = weatherService.getWeather();
        Weather weather = new Gson().fromJson(content.toString(), Weather.class);
        List test = (List) weather.getCurrent().get("weather");
        Map innerMap = (Map) test.get(0);
        String desc = (String) innerMap.get("description");

        /*System.out.println("temp: " + weather.getCurrent().get("temp"));
        System.out.println("pressure: " + weather.getCurrent().get("pressure"));
        System.out.println("humidity: " + weather.getCurrent().get("humidity"));
        System.out.println("wind_speed: " + weather.getCurrent().get("wind_speed"));
        System.out.println("description: " + desc);*/

        // SHOWING IN APPLICATION
        String str2 = weather.getCurrent().get("pressure").toString();
        double pressureAsDouble = fromStringToDouble(str2);
        int pressureAsInt = fromDoubleToInt(pressureAsDouble);
        String pressure = String.valueOf(pressureAsInt);

        String str3 = weather.getCurrent().get("humidity").toString();
        double humidityAsDouble = fromStringToDouble(str3);
        int humidityAsInt = fromDoubleToInt(humidityAsDouble);
        String humidity = String.valueOf(humidityAsInt);

        currentTemp.setText(weather.getCurrent().get("temp").toString());
        currentPressure.setText("Ciśnienie: " + pressure + "hPa");
        currentHumidity.setText("Wilgotność: " + humidity + "%");
        currentWindSpeed.setText(weather.getCurrent().get("wind_speed").toString());
        currentDescription.setText(desc);

        // dla daily
        //System.out.println(weather.getDaily()[0].getPressure()); // WORKS!
        //System.out.println(weather.getDaily()[0].getTemperature()); // WORKS!
        //System.out.println(weather.getDaily()[0].getSecondWeather()[0].getMain());
    }

    public double fromStringToDouble(String str) {
        double d = Double.parseDouble(str);
        return d;
    }

    public int fromDoubleToInt(double d) {
        int i = (int) d;
        return i;
    }
}
