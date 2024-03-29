package main;


import Database.JDBC;
import javafx.application.Application;
import java.io.IOException;
import java.util.Locale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**This class creates a scheduling and appointment application*/
public class Main extends Application {

    /**This method loads the initial page and makes the FXML stage.*/
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Login Form");
        stage.setScene(new Scene(root, 600, 335 ));
        stage.show();
    }

    public static void main(String[] args){
        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();

    }
}