package com.weather.pogodynka;

import com.weather.pogodynka.controller.MainWindowController;
import com.weather.pogodynka.controller.StartWindowController;
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

    public static void main(String[] args) {
        launch();
    }

    //private StartWindowController startWindowController = new StartWindowController();
    //private MainWindowController mainWindowController = new MainWindowController();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            ViewFactory viewFactory = new ViewFactory(new WeatherManager());
            viewFactory.showStartWindow();
            /*if () {

            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("view/MainWindow.fxml"));
        fxmlLoader.setController(mainWindowController);
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("view/css/main.css").toExternalForm();
        scene.getStylesheets().add(css);
        mainWindowController.weatherForecastForUserLocation();*/

        /*FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("view/StartWindow.fxml"));
        fxmlLoader.setController(startWindowController);
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();*/
    }




}
