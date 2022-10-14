package com.weather.pogodynka.view;

import com.weather.pogodynka.controller.APIKeyControllerWindow;
import com.weather.pogodynka.controller.BaseController;
import com.weather.pogodynka.controller.MainWindowController;
import com.weather.pogodynka.controller.StartWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewFactory {
    public void showAPIKeyWindow() {
        BaseController controller = new APIKeyControllerWindow(this, "APIKeyWindow.fxml");
        initializeStage(controller);
    }
    public void showStartWindow() {
        BaseController controller = new StartWindowController(this, "StartWindow.fxml");
        initializeStage(controller);
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(this, "MainWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        String css = getClass().getResource("css/main.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
    }
}