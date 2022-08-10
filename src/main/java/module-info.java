module com.weather.pogodynka {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.weather.pogodynka to javafx.fxml;
    exports com.weather.pogodynka;
    opens com.weather.pogodynka.controller;
}