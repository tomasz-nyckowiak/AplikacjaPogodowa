package com.weather.pogodynka;

import com.weather.pogodynka.controller.MainWindowController;
import com.weather.pogodynka.controller.StartWindowController;
import com.weather.pogodynka.controller.persistence.PersistenceAccess;
import com.weather.pogodynka.view.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

public class Launcher extends Application {
    private PersistenceAccess persistenceAccess = new PersistenceAccess();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(new WeatherManager());
        if (persistenceAccess.isCityNameFromFileValid()) {
            viewFactory.showMainWindow();
        } else {
            viewFactory.showStartWindow();
        }
    }







}
