package com.weather.pogodynka;

import com.weather.pogodynka.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch();
    }

    private MainWindowController mainWindowController = new MainWindowController();

    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("view/MainWindow.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("view/MainWindow.fxml"));
        fxmlLoader.setController(mainWindowController);
        //mainWindowController.init(); // nie działa!
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Prognoza pogody na najbliższe 5 dni!");
        stage.setScene(scene);
        mainWindowController.setTodayDate(); //działa!
        mainWindowController.weatherForecastForUserLocation();
        stage.show();

        /*InetAddress IP = InetAddress.getLocalHost();
        System.out.println("IP of my system is: " + IP.getHostAddress());

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        System.out.println(socket.getLocalAddress());
        socket.close();*/

        /*String urlString = "http://ipecho.net/plain";
        URL url = new URL(urlString);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            System.out.println(br.readLine());
        }*/
    }
}
