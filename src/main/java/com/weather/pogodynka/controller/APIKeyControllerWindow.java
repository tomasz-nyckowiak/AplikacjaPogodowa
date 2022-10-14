package com.weather.pogodynka.controller;

import com.weather.pogodynka.Constants;
import com.weather.pogodynka.controller.persistence.PersistenceAccess;
import com.weather.pogodynka.service.WeatherService;
import com.weather.pogodynka.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class APIKeyControllerWindow extends BaseController {
    @FXML
    private PasswordField secretKeyField;
    @FXML
    private Label informationForUser;
    @FXML
    private Button verifyKeyButton;
    @FXML
    private Button runProgramButton;

    private final WeatherService weatherService = new WeatherService();
    private final PersistenceAccess persistenceAccess = new PersistenceAccess();

    public APIKeyControllerWindow(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    public void secretKeyActionOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            verifySecretKey();
        }
    }

    @FXML
    public void verifySecretKey() {
        String secretKey = secretKeyField.getText();

        if (basicValidationForSecretKey(secretKey)) {
            StringBuffer content = weatherService.isSecretKeyValid(secretKey);
            String error = "Podano nieprawidłowy klucz!";
            if (error.equals(String.valueOf(content))) {
                informationForUser.setText(error);
                informationForUser.setTextFill(Color.valueOf("#e12121"));
                runProgramButton.setDisable(true);
            } else {
                informationForUser.setText("Klucz prawidłowy!");
                informationForUser.setTextFill(Color.GREEN);
                runProgramButton.setDisable(false);
                secretKeyField.setDisable(true);
                verifyKeyButton.setDisable(true);
            }
        } else {
            informationForUser.setText("Poprawny klucz składa się z 32 znaków!");
            informationForUser.setTextFill(Color.valueOf("#e12121"));
            runProgramButton.setDisable(true);
        }
    }

    private boolean basicValidationForSecretKey(String userInput) {
        String sampleCode = "00400f7test86API9955code00bd3322";
        if (userInput.isEmpty() || userInput.length() < sampleCode.length()) {
            return false;
        } else return true;
    }

    @FXML
    public void runApplication() {
        Constants.setSecretKey(secretKeyField.getText());
        if (persistenceAccess.isCityNameFromFileValid()) {
            viewFactory.showMainWindow();
        } else {
            viewFactory.showStartWindow();
        }
        Stage stage = (Stage) informationForUser.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
