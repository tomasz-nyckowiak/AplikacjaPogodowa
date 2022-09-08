package com.weather.pogodynka.controller;

import com.google.gson.Gson;
import com.weather.pogodynka.Dates;
import com.weather.pogodynka.WeatherManager;
import com.weather.pogodynka.model.Destination;
import com.weather.pogodynka.model.Weather;
import com.weather.pogodynka.model.WeatherData;
import com.weather.pogodynka.service.Geocoding;
import com.weather.pogodynka.service.Icons;
import com.weather.pogodynka.service.WeatherService;
import com.weather.pogodynka.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class MainWindowController extends BaseController {
    @FXML
    private TextField defaultLocation;
    @FXML
    private TextField chosenLocation;

    @FXML
    private Label currentDateAndTime;
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
    private Label localForecastDay1;
    @FXML
    private Label localForecastDay2;
    @FXML
    private Label localForecastDay3;
    @FXML
    private Label localForecastDay4;
    @FXML
    private Label localForecastDay5;
    @FXML
    private Label currentTemperature;
    @FXML
    private Label currentTemperature1;
    @FXML
    private Label currentWeather;
    @FXML
    private Label currentWeather1;
    @FXML
    private Label currentWeatherDescription;
    @FXML
    private Label currentWeatherDescription1;
    @FXML
    private Label destinationForecastDay1;
    @FXML
    private Label destinationForecastDay2;
    @FXML
    private Label destinationForecastDay3;
    @FXML
    private Label destinationForecastDay4;
    @FXML
    private Label destinationForecastDay5;
    @FXML
    private Label errorCityNotFound;
    @FXML
    private Label errorCityNotFound1;
    @FXML
    private Label searchResult;
    @FXML
    private Label searchResult1;

    @FXML
    private Button searchButton;
    @FXML
    private Button searchButton1;

    @FXML
    private ImageView myImageView;
    @FXML
    private ImageView myImageView1;
    @FXML
    private ImageView myImageView01;
    @FXML
    private ImageView myImageView02;
    @FXML
    private ImageView myImageView03;
    @FXML
    private ImageView myImageView04;
    @FXML
    private ImageView myImageView05;
    @FXML
    private ImageView myImageView06;
    @FXML
    private ImageView myImageView07;
    @FXML
    private ImageView myImageView08;
    @FXML
    private ImageView myImageView09;
    @FXML
    private ImageView myImageView10;

    private WeatherService weatherService = new WeatherService();
    private Geocoding geocoding = new Geocoding();
    WeatherData weatherData = new WeatherData();

    public MainWindowController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
    }

    public void weatherForecastForUserLocation() {
        TextField textField = new TextField("Zielona Góra");
        String userLocation = textField.textProperty().getValue();
        defaultLocation.setText(userLocation);
        geocoding.setUserInput(userLocation);
        StringBuffer content1 = geocoding.getDestination();
        Destination[] destination = new Gson().fromJson(content1.toString(), Destination[].class);

        double latitude = destination[0].getLat();
        double longitude = destination[0].getLon();
        String lat = weatherData.getCoordinates(latitude);
        String lon = weatherData.getCoordinates(longitude);

        weatherService.settingLat(lat);
        weatherService.settingLon(lon);

        Dates dates = new Dates();
        String[] days = dates.getForecastDays();
        String lokalny = dates.setTodayDate();

        day1.setText(days[0]);
        day2.setText(days[1]);
        day3.setText(days[2]);
        day4.setText(days[3]);
        day5.setText(days[4]);

        // CURRENT WEATHER
        StringBuffer content = weatherService.getWeather();
        Weather weather = new Gson().fromJson(content.toString(), Weather.class);
        List test = (List) weather.getCurrent().get("weather");
        Map innerMap = (Map) test.get(0);
        String desc = (String) innerMap.get("description");

        // GETTING ICON
        String iconCode = (String) innerMap.get("icon");
       // Image image = new Image("http://openweathermap.org/img/wn/10d@2x.png");
        Icons icons = new Icons();
        icons.setIconCode(iconCode);
        Image image = new Image(icons.getIconCode());
        myImageView.setImage(image);

        String temperatureAsString = weather.getCurrent().get("temp").toString();
        String temperature = weatherData.getTemperature(temperatureAsString);

        String pressureAsString = weather.getCurrent().get("pressure").toString();
        String pressure = weatherData.getPressure(pressureAsString);

        String humidityAsString = weather.getCurrent().get("humidity").toString();
        String humidity = weatherData.getHumidity(humidityAsString);

        String windSpeedAsString = weather.getCurrent().get("wind_speed").toString();
        String windSpeed = weatherData.getWindSpeed(windSpeedAsString);

        currentDateAndTime.setText(lokalny);
        currentTemperature.setText(temperature);
        currentTemperature.setFont(new Font("Arial", 18));
        currentWeather.setText(pressure + ", " + humidity + ", " + windSpeed + ",");
        currentWeatherDescription.setText(desc);

        // WEATHER FORECAST FOR NEXT 5 DAYS
        String[] forecastDay = new String[5];
        String[] TemperatureDay = new String[5];
        String[] PressureDay = new String[5];
        String[] HumidityDay = new String[5];
        String[] WindSpeedDay = new String[5];
        String[] DescriptionDay = new String[5];
        String[] IconsCodes = new String[5];

        for (int i = 0; i <= 4; i++) {
            TemperatureDay[i] = weather.getDaily()[i+1].getTemperature();
            PressureDay[i] = weather.getDaily()[i+1].getPressure();
            HumidityDay[i] = weather.getDaily()[i+1].getHumidity();
            WindSpeedDay[i] = weather.getDaily()[i+1].getWindSpeed();
            DescriptionDay[i] = weather.getDaily()[i+1].getSecondWeather()[0].getDescription();
            forecastDay[i] = weatherData.getTemperature(TemperatureDay[i]) + ", " + weatherData.getPressure(PressureDay[i]) + ", " +
                    weatherData.getHumidity(HumidityDay[i]) + ", " + weatherData.getWindSpeed(WindSpeedDay[i]) + ", " + DescriptionDay[i];
            IconsCodes[i] = weather.getDaily()[i+1].getSecondWeather()[0].getIcon();
        }

        icons.setIconCode(IconsCodes[0]);
        Image image01 = new Image(icons.getIconCode());
        myImageView01.setImage(image01);
        localForecastDay1.setText(forecastDay[0]);

        icons.setIconCode(IconsCodes[1]);
        Image image02 = new Image(icons.getIconCode());
        myImageView02.setImage(image02);
        localForecastDay2.setText(forecastDay[1]);

        icons.setIconCode(IconsCodes[2]);
        Image image03 = new Image(icons.getIconCode());
        myImageView03.setImage(image03);
        localForecastDay3.setText(forecastDay[2]);

        icons.setIconCode(IconsCodes[3]);
        Image image04 = new Image(icons.getIconCode());
        myImageView04.setImage(image04);
        localForecastDay4.setText(forecastDay[3]);

        icons.setIconCode(IconsCodes[4]);
        Image image05 = new Image(icons.getIconCode());
        myImageView05.setImage(image05);
        localForecastDay5.setText(forecastDay[4]);
    }

    @FXML
    public void search() {
        errorCityNotFound1.setText("");
        if (cityNameIsValid()) {
            String userDestinationInput;
            userDestinationInput = chosenLocation.getText();
            geocoding.setUserInput(userDestinationInput);
            StringBuffer content = geocoding.getDestination();
            Destination[] destination = new Gson().fromJson(content.toString(), Destination[].class);

            double latitude = destination[0].getLat();
            double longitude = destination[0].getLon();
            String lat = weatherData.getCoordinates(latitude);
            String lon = weatherData.getCoordinates(longitude);

            weatherService.settingLat(lat);
            weatherService.settingLon(lon);

            String country = destination[0].getCountry();
            String namePL = destination[0].getNames().get("pl").toString();

            searchResult1.setText(namePL + ", " + country);

            StringBuffer contentWeather = weatherService.getWeather();
            Weather weather = new Gson().fromJson(contentWeather.toString(), Weather.class);

            // CURRENT WEATHER
            List test = (List) weather.getCurrent().get("weather");
            Map innerMap = (Map) test.get(0);
            String desc = (String) innerMap.get("description");

            // GETTING ICON
            String iconCode = (String) innerMap.get("icon");
            Icons icons = new Icons();
            icons.setIconCode(iconCode);
            Image image = new Image(icons.getIconCode());
            myImageView1.setImage(image);

            String temperatureAsString = weather.getCurrent().get("temp").toString();
            String temperature = weatherData.getTemperature(temperatureAsString);

            String pressureAsString = weather.getCurrent().get("pressure").toString();
            String pressure = weatherData.getPressure(pressureAsString);

            String humidityAsString = weather.getCurrent().get("humidity").toString();
            String humidity = weatherData.getHumidity(humidityAsString);

            String windSpeedAsString = weather.getCurrent().get("wind_speed").toString();
            String windSpeed = weatherData.getWindSpeed(windSpeedAsString);

            currentTemperature1.setText(temperature);
            currentTemperature1.setFont(new Font("Arial", 18));
            currentWeather1.setText(pressure + ", " + humidity + ", " + windSpeed + ",");
            currentWeatherDescription1.setText(desc);

            // DAILY
            String[] forecastDay = new String[5];
            String[] TemperatureDay = new String[5];
            String[] PressureDay = new String[5];
            String[] HumidityDay = new String[5];
            String[] WindSpeedDay = new String[5];
            String[] DescriptionDay = new String[5];
            String[] IconsCodes = new String[5];

            for (int i = 0; i <= 4; i++) {
                TemperatureDay[i] = weather.getDaily()[i+1].getTemperature();
                PressureDay[i] = weather.getDaily()[i+1].getPressure();
                HumidityDay[i] = weather.getDaily()[i+1].getHumidity();
                WindSpeedDay[i] = weather.getDaily()[i+1].getWindSpeed();
                DescriptionDay[i] = weather.getDaily()[i+1].getSecondWeather()[0].getDescription();
                forecastDay[i] = weatherData.getTemperature(TemperatureDay[i]) + ", " + weatherData.getPressure(PressureDay[i]) + ", " +
                        weatherData.getHumidity(HumidityDay[i]) + ", " + weatherData.getWindSpeed(WindSpeedDay[i]) + ", " + DescriptionDay[i];
                IconsCodes[i] = weather.getDaily()[i+1].getSecondWeather()[0].getIcon();
            }

            icons.setIconCode(IconsCodes[0]);
            Image image06 = new Image(icons.getIconCode());
            myImageView06.setImage(image06);
            destinationForecastDay1.setText(forecastDay[0]);

            icons.setIconCode(IconsCodes[1]);
            Image image07 = new Image(icons.getIconCode());
            myImageView07.setImage(image07);
            destinationForecastDay2.setText(forecastDay[1]);

            icons.setIconCode(IconsCodes[2]);
            Image image08 = new Image(icons.getIconCode());
            myImageView08.setImage(image08);
            destinationForecastDay3.setText(forecastDay[2]);

            icons.setIconCode(IconsCodes[3]);
            Image image09 = new Image(icons.getIconCode());
            myImageView09.setImage(image09);
            destinationForecastDay4.setText(forecastDay[3]);

            icons.setIconCode(IconsCodes[4]);
            Image image10 = new Image(icons.getIconCode());
            myImageView10.setImage(image10);
            destinationForecastDay5.setText(forecastDay[4]);
        }
    }

    @FXML
    public void searchLeft() {
        errorCityNotFound.setText("");
        if (cityNameIsValid()) {
            String userDestinationInput;
            userDestinationInput = defaultLocation.getText();
            geocoding.setUserInput(userDestinationInput);
            StringBuffer content = geocoding.getDestination();
            Destination[] destination = new Gson().fromJson(content.toString(), Destination[].class);

            double latitude = destination[0].getLat();
            double longitude = destination[0].getLon();
            String lat = weatherData.getCoordinates(latitude);
            String lon = weatherData.getCoordinates(longitude);

            weatherService.settingLat(lat);
            weatherService.settingLon(lon);

            String country = destination[0].getCountry();
            String namePL = destination[0].getNames().get("pl").toString();

            searchResult.setText(namePL + ", " + country);

            StringBuffer contentWeather = weatherService.getWeather();
            Weather weather = new Gson().fromJson(contentWeather.toString(), Weather.class);

            // DAILY
            String[] forecastDay = new String[5];
            String[] TemperatureDay = new String[5];
            String[] PressureDay = new String[5];
            String[] HumidityDay = new String[5];
            String[] WindSpeedDay = new String[5];
            String[] DescriptionDay = new String[5];
            String[] IconsCodes = new String[5];

            for (int i = 0; i <= 4; i++) {
                TemperatureDay[i] = weather.getDaily()[i+1].getTemperature();
                PressureDay[i] = weather.getDaily()[i+1].getPressure();
                HumidityDay[i] = weather.getDaily()[i+1].getHumidity();
                WindSpeedDay[i] = weather.getDaily()[i+1].getWindSpeed();
                DescriptionDay[i] = weather.getDaily()[i+1].getSecondWeather()[0].getDescription();
                forecastDay[i] = weatherData.getTemperature(TemperatureDay[i]) + ", " + weatherData.getPressure(PressureDay[i]) + ", " +
                        weatherData.getHumidity(HumidityDay[i]) + ", " + weatherData.getWindSpeed(WindSpeedDay[i]) + ", " + DescriptionDay[i];
                IconsCodes[i] = weather.getDaily()[i+1].getSecondWeather()[0].getIcon();
            }

            Icons icons = new Icons();

            icons.setIconCode(IconsCodes[0]);
            Image image01 = new Image(icons.getIconCode());
            myImageView01.setImage(image01);
            localForecastDay1.setText(forecastDay[0]);

            icons.setIconCode(IconsCodes[1]);
            Image image02 = new Image(icons.getIconCode());
            myImageView02.setImage(image02);
            localForecastDay2.setText(forecastDay[1]);

            icons.setIconCode(IconsCodes[2]);
            Image image03 = new Image(icons.getIconCode());
            myImageView03.setImage(image03);
            localForecastDay3.setText(forecastDay[2]);

            icons.setIconCode(IconsCodes[3]);
            Image image04 = new Image(icons.getIconCode());
            myImageView04.setImage(image04);
            localForecastDay4.setText(forecastDay[3]);

            icons.setIconCode(IconsCodes[4]);
            Image image05 = new Image(icons.getIconCode());
            myImageView05.setImage(image05);
            localForecastDay5.setText(forecastDay[4]);
        }
    }

    @FXML
    public void actionOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            search();
        }
    }

    private boolean cityNameIsValid() {
        String pattern = "^([a-zA-Z\u0080-\u024F]+(?:(\\.) |-| |'))*[a-zA-Z\u0080-\u024F]*$";
        try {
            if (chosenLocation.getText().isEmpty()) {
                errorCityNotFound.setText("Nie wybrano żadnego miasta!");
                return false;
            }
            if (!chosenLocation.getText().matches(pattern)) {
                errorCityNotFound.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
                return false;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            errorCityNotFound.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
            return false;
        }
        return true;
    }
}
