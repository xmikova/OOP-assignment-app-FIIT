package org.example.app;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that launches the app to main page.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application with main login page.
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainlogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
