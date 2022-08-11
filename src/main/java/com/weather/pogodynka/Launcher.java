package com.weather.pogodynka;

import com.weather.pogodynka.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        stage.show();
    }
}
