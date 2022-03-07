package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    void onLoginClick(ActionEvent event) {
        System.out.println("Clicked");
    }

}
