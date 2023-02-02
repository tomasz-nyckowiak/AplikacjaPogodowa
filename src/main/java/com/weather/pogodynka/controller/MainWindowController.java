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
    private final WeatherData weatherData = new WeatherData();
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
        Weather weather = new Gson().fromJson(content.toString(), Weather.class);
        Current current = weather.getCurrent();
        Optional<WeatherDetails> weatherDetails = current.getWeatherDetails().stream().findFirst();
        String desc = weatherDetails.map(WeatherDetails::getDescription).orElse("brak danych");

        // GETTING ICON
        String iconCode = weatherDetails.map(WeatherDetails::getIcon).orElse("domyślny kod ikony");
        Icons icons = new Icons();
        Image image = new Image(icons.getImageCode(iconCode));
        icon.setImage(image);

        String temperatureAsString = String.valueOf(weather.getCurrent().getTemp());
        String temperature = weatherData.getTemperature(temperatureAsString);

        String pressureAsString = String.valueOf(weather.getCurrent().getPressure());
        String pressure = weatherData.getPressure(pressureAsString);

        String humidityAsString = String.valueOf(weather.getCurrent().getHumidity());
        String humidity = weatherData.getHumidity(humidityAsString);

        String windSpeedAsString = String.valueOf(weather.getCurrent().getWindSpeed());
        String windSpeed = weatherData.getWindSpeed(windSpeedAsString);

        currentWeatherLabels[0].setText(temperature);
        currentWeatherLabels[0].setFont(new Font("Arial", 18));
        currentWeatherLabels[1].setText(pressure + ", " + humidity + ", " + windSpeed + ",");
        currentWeatherLabels[2].setText(desc);
    }

    private void searchCity(TextField cityName, Label searchResult, Label[] forecastDescription, ImageView[] weatherIcons, Label errorCityNotFound,
                            ImageView currentWeatherIcon, Label[] currentWeatherLabels) {
        try {
            String secretKey = Constants.getSecretKey();
            String userDestinationInput = cityName.getText();
            geocoding.setLabel(informationForUser);
            StringBuffer content = geocoding.getDestination(secretKey, userDestinationInput);
            Destination[] destination = new Gson().fromJson(content.toString(), Destination[].class);

            double latitude = destination[0].getLat();
            double longitude = destination[0].getLon();
            String lat = weatherData.getCoordinates(latitude);
            String lon = weatherData.getCoordinates(longitude);

            String country = destination[0].getCountry();
            String namePL = destination[0].getNames().get("pl").toString();

            searchResult.setText(namePL + ", " + country);

            StringBuffer contentWeather = weatherService.getWeather(secretKey, lat, lon);
            Weather weather = new Gson().fromJson(contentWeather.toString(), Weather.class);
            todayWeather(contentWeather, currentWeatherIcon, currentWeatherLabels);

            // WEATHER FORECAST DATA FOR NEXT 5 DAYS (DAILY)
            String[] forecastDay = new String[5];
            String[] temperatureDay = new String[5];
            String[] pressureDay = new String[5];
            String[] humidityDay = new String[5];
            String[] windSpeedDay = new String[5];
            String[] descriptionDay = new String[5];
            String[] iconsCodes = new String[5];

            int NUMBER_OF_DAYS_FOR_FORECAST = 5;
            for (int i = 0; i < NUMBER_OF_DAYS_FOR_FORECAST; i++) {
                temperatureDay[i] = weather.getDaily()[i + 1].getTemperature();
                pressureDay[i] = weather.getDaily()[i + 1].getPressure();
                humidityDay[i] = weather.getDaily()[i + 1].getHumidity();
                windSpeedDay[i] = weather.getDaily()[i + 1].getWindSpeed();
                descriptionDay[i] = weather.getDaily()[i + 1].getSecondWeather()[0].getDescription();
                forecastDay[i] = weatherData.getTemperature(temperatureDay[i]) + ", " + weatherData.getPressure(pressureDay[i]) + ", " +
                        weatherData.getHumidity(humidityDay[i]) + ", " + weatherData.getWindSpeed(windSpeedDay[i]) + ", " + descriptionDay[i];
                iconsCodes[i] = weather.getDaily()[i + 1].getSecondWeather()[0].getIcon();
            }

            Icons icons = new Icons();
            for (int i = 0; i < iconsCodes.length; i++) {
                setImages(icons, iconsCodes, i, weatherIcons[i]);
            }

            for (int i = 0; i < forecastDay.length; i++) {
                setWeatherForecastDescription(forecastDay, i, forecastDescription[i]);
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            errorCityNotFound.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
        }
    }

    private void setImages(Icons icons,  String[] iconsCodes, int iconIndex, ImageView imageView) {
        Image image = new Image(icons.getImageCode(iconsCodes[iconIndex]));
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
            e.printStackTrace();
            errorCityNotFound.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
            return false;
        }
        return true;
    }

    @FXML
    public void resetDefaultCity() {
        persistenceAccess.setLabel(informationForUser);
        persistenceAccess.resetDefaultCityName();
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
            e.printStackTrace();
            informationForUser.setText("Wystąpił błąd!");
        }
    }
}
