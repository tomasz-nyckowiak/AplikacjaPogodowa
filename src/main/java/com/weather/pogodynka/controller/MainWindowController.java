package com.weather.pogodynka.controller;

import com.google.gson.Gson;
import com.weather.pogodynka.Constants;
import com.weather.pogodynka.Dates;
import com.weather.pogodynka.UserDefaultLocation;
import com.weather.pogodynka.controller.persistence.PersistenceAccess;
import com.weather.pogodynka.model.*;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
    private Label currentTemperature1;
    @FXML
    private Label currentTemperature2;
    @FXML
    private Label currentWeather1;
    @FXML
    private Label currentWeather2;
    @FXML
    private Label currentWeatherDescription1;
    @FXML
    private Label currentWeatherDescription2;

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
    private Label errorCityNotFoundLeft;
    @FXML
    private Label errorCityNotFoundRight;
    @FXML
    private Label searchResultLeft;
    @FXML
    private Label searchResultRight;
    @FXML
    private Label informationForUser;

    @FXML
    private Button searchButtonLeft;
    @FXML
    private Button searchButtonRight;
    @FXML
    private Button resetDefaultCityButton;

    @FXML
    private ImageView myImageView1;
    @FXML
    private ImageView myImageView2;

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

    private final WeatherService weatherService = new WeatherService();
    private final Geocoding geocoding = new Geocoding();
    //private final WeatherData weatherData = new WeatherData();
    private final UserDefaultLocation userDefaultLocation = new UserDefaultLocation();
    private final PersistenceAccess persistenceAccess = new PersistenceAccess();

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public void weatherForecastForUserLocation(String location) {
        TextField textField = new TextField(location);
        String userLocation = textField.textProperty().getValue();
        defaultLocation.setText(userLocation);

        Dates dates = new Dates();
        String[] days = dates.getForecastDays();
        String localDate = dates.setTodayDate();

        Label[] labels = {day1, day2, day3, day4, day5};

        for (int i = 0; i < days.length; i++) {
            labels[i].setText(days[i]);
        }

        currentDateAndTime.setText(localDate);
        searchLeft();
    }

    @FXML
    public void searchRight() {
        clearLabels(errorCityNotFoundRight);
        if (cityNameOnRightIsValid()) {
            Label[] destinationLabels = {destinationForecastDay1, destinationForecastDay2, destinationForecastDay3, destinationForecastDay4, destinationForecastDay5};
            ImageView[] weatherIcons = {myImageView06, myImageView07, myImageView08, myImageView09, myImageView10};
            ImageView currentWeatherIcon = myImageView2;
            Label[] currentWeatherLabels = {currentTemperature2, currentWeather2, currentWeatherDescription2};
            searchCity(chosenLocation, searchResultRight, destinationLabels, weatherIcons, errorCityNotFoundRight,
                    currentWeatherIcon, currentWeatherLabels);
        }
    }

    @FXML
    public void searchLeft() {
        clearLabels(errorCityNotFoundLeft);
        if (cityNameOnLeftIsValid()) {
            Label[] destinationLabels = {localForecastDay1, localForecastDay2, localForecastDay3, localForecastDay4, localForecastDay5};
            ImageView[] weatherIcons = {myImageView01, myImageView02, myImageView03, myImageView04, myImageView05};
            ImageView currentWeatherIcon = myImageView1;
            Label[] currentWeatherLabels = {currentTemperature1, currentWeather1, currentWeatherDescription1};
            searchCity(defaultLocation, searchResultLeft, destinationLabels, weatherIcons, errorCityNotFoundLeft,
                    currentWeatherIcon, currentWeatherLabels);
        }
    }

    private void todayWeather(StringBuffer content, ImageView icon, Label[] currentWeatherLabels) {
        String[] output = Current.gettingCurrentWeatherOutput(content);
        //System.out.println(Arrays.toString(output));

        // GETTING ICON
        String iconCode = Icons.imageCode;
        Image image = new Image(Icons.getImageCode(iconCode));
        icon.setImage(image);

        currentWeatherLabels[0].setText(output[0]);
        currentWeatherLabels[0].setFont(new Font("Arial", 18));
        currentWeatherLabels[1].setText(output[1] + ", " + output[2] + ", " + output[3] + ",");
        currentWeatherLabels[2].setText(output[4]);
    }

    private void searchCity(TextField cityName, Label searchResult, Label[] forecastDescription, ImageView[] weatherIcons, Label errorCityNotFound,
                            ImageView currentWeatherIcon, Label[] currentWeatherLabels) {
        try {
            String userDestinationInput = cityName.getText();
            StringBuffer content = geocoding.getDestination(userDestinationInput);
            String[] output = Destination.gettingDestinationOutput(content);
            String country = output[0];
            String namePL = output[1];
            String lat = output[2];
            String lon = output[3];

            searchResult.setText(namePL + ", " + country);

            StringBuffer contentWeather = weatherService.getWeather(lat, lon);
            //System.out.println(contentWeather);
            todayWeather(contentWeather, currentWeatherIcon, currentWeatherLabels);

            String[] weatherOutput = Weather.gettingWeatherOutput(contentWeather);

            for (int i = 0; i < weatherOutput.length; i++) {
                setWeatherForecastDescription(weatherOutput, i, forecastDescription[i]);
            }

            // SETTING ICONS
            String[] iconsCodes = Icons.imagesCodes;
            for (int i = 0; i < iconsCodes.length; i++) {
                setImages(iconsCodes, i, weatherIcons[i]);
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            errorCityNotFound.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
        }
    }

    private void setImages(String[] iconsCodes, int iconIndex, ImageView imageView) {
        Image image = new Image(Icons.getImageCode(iconsCodes[iconIndex]));
        imageView.setImage(image);
    }

    private void setWeatherForecastDescription(String[] forecastDay, int descriptionIndex, Label localForecastDay) {
        localForecastDay.setText(forecastDay[descriptionIndex]);
    }

    @FXML
    public void defaultLocationActionOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchLeft();
        }
    }

    @FXML
    public void actionOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchRight();
        }
    }

    private void clearLabels(Label errorCityNotFound) {
        errorCityNotFound.setText("");
        informationForUser.setText("");
    }

    private boolean cityNameOnLeftIsValid() {
        return validateCity(defaultLocation, errorCityNotFoundLeft);
    }

    private boolean cityNameOnRightIsValid() {
        return validateCity(chosenLocation, errorCityNotFoundRight);
    }

    private boolean validateCity(TextField cityName, Label errorCityNotFound) {
        try {
            if (cityName.getText().isEmpty()) {
                errorCityNotFound.setText("Nie wybrano żadnego miasta!");
                return false;
            }
            if (!cityName.getText().matches(Constants.PATTERN)) {
                errorCityNotFound.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
                return false;
            }
        } catch (NullPointerException e) {
            errorCityNotFound.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
            return false;
        }
        return true;
    }

    @FXML
    public void resetDefaultCity() {
        if (Objects.equals(persistenceAccess.resetDefaultCityName(), "success")) {
            informationForUser.setText("Usunięto domyślne miasto!");
            informationForUser.setTextFill(Color.GREEN);
        } else {
            informationForUser.setText("Wystąpił błąd! Spróbuj później!");
            informationForUser.setTextFill(Color.valueOf("#e12121"));
        }
    }

    @FXML
    private void initialize() {
        try {
            String userLocationFromFile = persistenceAccess.loadUserCityNameFromFile(persistenceAccess.getMyFilePath());
            if (userLocationFromFile == null) {
                String userLocation = userDefaultLocation.getUserDefaultLocation();
                weatherForecastForUserLocation(userLocation);
            } else {
                weatherForecastForUserLocation(userLocationFromFile);
            }
        } catch (NullPointerException e) {
            informationForUser.setText("Wystąpił błąd!");
            informationForUser.setTextFill(Color.valueOf("#e12121"));
        }
    }
}
