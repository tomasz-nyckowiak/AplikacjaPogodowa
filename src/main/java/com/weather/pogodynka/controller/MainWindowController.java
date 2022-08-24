package com.weather.pogodynka.controller;

import com.google.gson.Gson;
import com.weather.pogodynka.model.Weather;
import com.weather.pogodynka.model.WeatherData;
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
    @FXML
    private Label forecastDay1;

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

        // SHOWING IN APPLICATION
        // CURRENT WEATHER
        WeatherData weatherData = new WeatherData();

        String temperatureAsString = weather.getCurrent().get("temp").toString();
        String temperature = weatherData.getTemperature(temperatureAsString);

        String pressureAsString = weather.getCurrent().get("pressure").toString();
        String pressure = weatherData.getPressure(pressureAsString);

        String humidityAsString = weather.getCurrent().get("humidity").toString();
        String humidity = weatherData.getHumidity(humidityAsString);

        String windSpeedAsString = weather.getCurrent().get("wind_speed").toString();
        String windSpeed = weatherData.getWindSpeed(windSpeedAsString);

        currentTemp.setText("Temperatura: " + temperature);
        currentPressure.setText("Ciśnienie: " + pressure);
        currentHumidity.setText("Wilgotność: " + humidity);
        currentWindSpeed.setText("Prędkość wiatru: " + windSpeed);
        currentDescription.setText(desc);

        // DAILY
        String day1Temp = weather.getDaily()[0].getTemperature();
        String day1Press = weather.getDaily()[0].getPressure();
        String day1Hum = weather.getDaily()[0].getHumidity();
        String day1Wind = weather.getDaily()[0].getWindSpeed();
        String day1Desc = weather.getDaily()[0].getSecondWeather()[0].getDescription();

        System.out.println(weather.getDaily().length);
        //System.out.println(weather.getDaily()[0].getPressure()); // WORKS!
        //System.out.println(weather.getDaily()[0].getTemperature()); // WORKS!
        //System.out.println(weather.getDaily()[0].getSecondWeather()[0].getMain());
    }
}
