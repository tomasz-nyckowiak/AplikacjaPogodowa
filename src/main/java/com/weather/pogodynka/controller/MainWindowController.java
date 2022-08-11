package com.weather.pogodynka.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainWindowController {

    @FXML
    private TextField chosenLocation;

    @FXML
    private TextField defaultLocation;

    @FXML
    private TextField todayDate;

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
            System.out.println(days[i]);
        }
        day1.setText(days[0]);
        day2.setText(days[1]);
        day3.setText(days[2]);
        day4.setText(days[3]);
        day5.setText(days[4]);



    }

}
