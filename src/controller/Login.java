package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {


    @FXML
    private Label LabelLocation;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField useridInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Initialized!");

        LabelLocation.setText("Salt Lake City");


    }


    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        System.out.println("Clicked");

        Parent root = FXMLLoader.load(getClass().getResource("/view/Customer.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

}
