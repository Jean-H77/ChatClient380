module com.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.desktop;

    opens com.client to javafx.fxml;
    exports com.client;
    exports com.client.controllers;
    opens com.client.controllers to javafx.fxml;
}