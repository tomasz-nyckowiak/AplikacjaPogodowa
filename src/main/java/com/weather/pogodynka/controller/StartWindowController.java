package com.weather.pogodynka.controller;

import com.google.gson.Gson;
import com.weather.pogodynka.UserDefaultLocation;
import com.weather.pogodynka.WeatherManager;
import com.weather.pogodynka.controller.persistence.PersistenceAccess;
import com.weather.pogodynka.model.Destination;
import com.weather.pogodynka.model.WeatherData;
import com.weather.pogodynka.service.Geocoding;
import com.weather.pogodynka.service.WeatherService;
import com.weather.pogodynka.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class StartWindowController extends BaseController {
    @FXML
    private TextField chosenCity;
    @FXML
    private CheckBox myCheckBox;

    @FXML
    private Label errorLabel;
    @FXML
    private Label searchResult;

    @FXML
    private Button searchCityButton;
    @FXML
    private Button showWeatherButton;

    private Geocoding geocoding = new Geocoding();
    private WeatherData weatherData = new WeatherData();
    private WeatherService weatherService = new WeatherService();
    private UserDefaultLocation userDefaultLocation = new UserDefaultLocation();
    private PersistenceAccess persistenceAccess = new PersistenceAccess();

    private boolean searchedCityIsValid = false;

    public StartWindowController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
    }

    @FXML
    public void searchCity() {
        clearFields();
        try {
            if (cityNameIsValid()) {
                String userInput = chosenCity.getText();
                geocoding.setUserInput(userInput);
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
                searchedCityIsValid = true;
                showWeatherButton.setDisable(false);
                myCheckBox.setDisable(false);

                userDefaultLocation.settingUserLocation(namePL);
            }
        } catch (NullPointerException e) {
            //e.printStackTrace();
            errorLabel.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
        } catch (ArrayIndexOutOfBoundsException e) {
            errorLabel.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
        }
    }

    @FXML
    public void actionOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchCity();
        }
    }

    @FXML
    void rememberMyCity() {

    }

    private void clearFields() {
        errorLabel.setText("");
        searchResult.setText("");
        myCheckBox.setDisable(true);
        showWeatherButton.setDisable(true);
    }

    private boolean cityNameIsValid() {
        String pattern = "^([a-zA-Z\u0080-\u024F]+(?:(\\.) |-| |'))*[a-zA-Z\u0080-\u024F]*$";
        try {
            if (chosenCity.getText().isEmpty()) {
                errorLabel.setText("Nie wybrano żadnego miasta!");
                return false;
            }
            if (!chosenCity.getText().matches(pattern)) {
                errorLabel.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
                return false;
            }
        } catch (NullPointerException e) {
            //e.printStackTrace();
            errorLabel.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
            return false;
        }
        return true;
    }

    @FXML
    public void saveAndShowWeather() {
        if (searchedCityIsValid) {
            if (myCheckBox.isSelected()) {
                String userCityName = userDefaultLocation.getUserDefaultLocation();
                persistenceAccess.saveUserCityNameToFile(userCityName);
            }
            viewFactory.showMainWindow();
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            viewFactory.closeStage(stage);
        }
    }
}
