package com.weather.pogodynka.controller;

import com.google.gson.Gson;
import com.weather.pogodynka.Dates;
import com.weather.pogodynka.model.Destination;
import com.weather.pogodynka.model.Weather;
import com.weather.pogodynka.model.WeatherData;
import com.weather.pogodynka.service.Geocoding;
import com.weather.pogodynka.service.WeatherService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.lang.reflect.Type;
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
    private Label errorCityNotFound;
    @FXML
    private Label searchResult;

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
    @FXML
    private Label forecastDay2;
    @FXML
    private Label forecastDay3;
    @FXML
    private Label forecastDay4;
    @FXML
    private Label forecastDay5;

    @FXML
    private Label destinationDay1;
    @FXML
    private Label destinationDay2;
    @FXML
    private Label destinationDay3;
    @FXML
    private Label destinationDay4;
    @FXML
    private Label destinationDay5;

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

    private WeatherService weatherService = new WeatherService();
    private Geocoding geocoding = new Geocoding();
    WeatherData weatherData = new WeatherData();

    public void setTodayDate() {
        LocalDate obj = LocalDate.now();
        String today = obj.toString();
        todayDate.setText(today);
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

        // WEATHER FORECAST FOR NEXT 5 DAYS
        String[] forecastDay = new String[5];
        String[] TemperatureDay = new String[5];
        String[] PressureDay = new String[5];
        String[] HumidityDay = new String[5];
        String[] WindSpeedDay = new String[5];
        String[] DescriptionDay = new String[5];

        for (int i = 0; i <= 4; i++) {
            TemperatureDay[i] = weather.getDaily()[i+1].getTemperature();
            PressureDay[i] = weather.getDaily()[i+1].getPressure();
            HumidityDay[i] = weather.getDaily()[i+1].getHumidity();
            WindSpeedDay[i] = weather.getDaily()[i+1].getWindSpeed();
            DescriptionDay[i] = weather.getDaily()[i+1].getSecondWeather()[0].getDescription();
            forecastDay[i] = weatherData.getTemperature(TemperatureDay[i]) + ", " + weatherData.getPressure(PressureDay[i]) + ", " +
                    weatherData.getHumidity(HumidityDay[i]) + ", " + weatherData.getWindSpeed(WindSpeedDay[i]) + ", " + DescriptionDay[i];
        }

        forecastDay1.setText(forecastDay[0]);
        forecastDay2.setText(forecastDay[1]);
        forecastDay3.setText(forecastDay[2]);
        forecastDay4.setText(forecastDay[3]);
        forecastDay5.setText(forecastDay[4]);
    }

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
        //WeatherData weatherData = new WeatherData();

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
        String day1Temp = weather.getDaily()[1].getTemperature();
        String day1Press = weather.getDaily()[1].getPressure();
        String day1Hum = weather.getDaily()[1].getHumidity();
        String day1Wind = weather.getDaily()[1].getWindSpeed();
        String day1Desc = weather.getDaily()[1].getSecondWeather()[0].getDescription();

        String dataDay1 = weatherData.getTemperature(day1Temp) + ", " + weatherData.getPressure(day1Press) + ", " +
                weatherData.getHumidity(day1Hum) + ", " + weatherData.getWindSpeed(day1Wind) + ", " + day1Desc;

        forecastDay1.setText(dataDay1);

        String day2Temp = weather.getDaily()[2].getTemperature();
        String day2Press = weather.getDaily()[2].getPressure();
        String day2Hum = weather.getDaily()[2].getHumidity();
        String day2Wind = weather.getDaily()[2].getWindSpeed();
        String day2Desc = weather.getDaily()[2].getSecondWeather()[0].getDescription();

        String dataDay2 = weatherData.getTemperature(day2Temp) + ", " + weatherData.getPressure(day2Press) + ", " +
                weatherData.getHumidity(day2Hum) + ", " + weatherData.getWindSpeed(day2Wind) + ", " + day2Desc;

        forecastDay2.setText(dataDay2);

        String day3Temp = weather.getDaily()[3].getTemperature();
        String day3Press = weather.getDaily()[3].getPressure();
        String day3Hum = weather.getDaily()[3].getHumidity();
        String day3Wind = weather.getDaily()[3].getWindSpeed();
        String day3Desc = weather.getDaily()[3].getSecondWeather()[0].getDescription();

        String dataDay3 = weatherData.getTemperature(day3Temp) + ", " + weatherData.getPressure(day3Press) + ", " +
                weatherData.getHumidity(day3Hum) + ", " + weatherData.getWindSpeed(day3Wind) + ", " + day3Desc;

        forecastDay3.setText(dataDay3);

        String day4Temp = weather.getDaily()[4].getTemperature();
        String day4Press = weather.getDaily()[4].getPressure();
        String day4Hum = weather.getDaily()[4].getHumidity();
        String day4Wind = weather.getDaily()[4].getWindSpeed();
        String day4Desc = weather.getDaily()[4].getSecondWeather()[0].getDescription();

        String dataDay4 = weatherData.getTemperature(day4Temp) + ", " + weatherData.getPressure(day4Press) + ", " +
                weatherData.getHumidity(day4Hum) + ", " + weatherData.getWindSpeed(day4Wind) + ", " + day4Desc;

        forecastDay4.setText(dataDay4);

        String day5Temp = weather.getDaily()[5].getTemperature();
        String day5Press = weather.getDaily()[5].getPressure();
        String day5Hum = weather.getDaily()[5].getHumidity();
        String day5Wind = weather.getDaily()[5].getWindSpeed();
        String day5Desc = weather.getDaily()[5].getSecondWeather()[0].getDescription();

        String dataDay5 = weatherData.getTemperature(day5Temp) + ", " + weatherData.getPressure(day5Press) + ", " +
                weatherData.getHumidity(day5Hum) + ", " + weatherData.getWindSpeed(day5Wind) + ", " + day5Desc;

        forecastDay5.setText(dataDay5);
    }

    @FXML
    void search() {
        errorCityNotFound.setText("");
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

            //String name = destination[0].getName();
            String country = destination[0].getCountry();
            String namePL = destination[0].getNames().get("pl").toString();

            searchResult.setText(namePL + ", " + country);

            Dates dates = new Dates();
            String[] days = dates.getForecastDays();

            destinationDay1.setText(days[0]);
            destinationDay2.setText(days[1]);
            destinationDay3.setText(days[2]);
            destinationDay4.setText(days[3]);
            destinationDay5.setText(days[4]);

            StringBuffer contentWeather = weatherService.getWeather();
            Weather weather = new Gson().fromJson(contentWeather.toString(), Weather.class);

            // DAILY
            String[] forecastDay = new String[5];
            String[] TemperatureDay = new String[5];
            String[] PressureDay = new String[5];
            String[] HumidityDay = new String[5];
            String[] WindSpeedDay = new String[5];
            String[] DescriptionDay = new String[5];

            for (int i = 0; i <= 4; i++) {
                TemperatureDay[i] = weather.getDaily()[i+1].getTemperature();
                PressureDay[i] = weather.getDaily()[i+1].getPressure();
                HumidityDay[i] = weather.getDaily()[i+1].getHumidity();
                WindSpeedDay[i] = weather.getDaily()[i+1].getWindSpeed();
                DescriptionDay[i] = weather.getDaily()[i+1].getSecondWeather()[0].getDescription();
                forecastDay[i] = weatherData.getTemperature(TemperatureDay[i]) + ", " + weatherData.getPressure(PressureDay[i]) + ", " +
                        weatherData.getHumidity(HumidityDay[i]) + ", " + weatherData.getWindSpeed(WindSpeedDay[i]) + ", " + DescriptionDay[i];
            }

            destinationForecastDay1.setText(forecastDay[0]);
            destinationForecastDay2.setText(forecastDay[1]);
            destinationForecastDay3.setText(forecastDay[2]);
            destinationForecastDay4.setText(forecastDay[3]);
            destinationForecastDay5.setText(forecastDay[4]);
        }
    }

    private boolean cityNameIsValid() {
        String pattern = "^([a-zA-Z\u0080-\u024F]+(?:(\\.) |-| |'))*[a-zA-Z\u0080-\u024F]*$";
        if (chosenLocation.getText().isEmpty()) {
            errorCityNotFound.setText("Nie wybrano żadnego miasta!");
            return false;
        }
        if (!chosenLocation.getText().matches(pattern)) {
            errorCityNotFound.setText("Nie znaleziono! Sprawdź czy wpisana nazwa jest poprawna!");
            return false;
        }
        return true;
    }


}
