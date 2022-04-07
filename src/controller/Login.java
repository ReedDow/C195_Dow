package controller;

import DBAccess.DBLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Log;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    public Label title;

    @FXML
    private Label LabelLocation;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField useridInput;



    /**This method sets an information alert that can be customized in subsequent methods.
     * @param aTitle The title of the alert.
     * @param aHeader the header message of the alert.
     * @param aContent the main message of the alert. */
    public boolean alert(String aTitle, String aHeader, String aContent) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle(aTitle);
        alert1.setHeaderText(aHeader);
        alert1.setContentText(aContent);
        alert1.showAndWait();
        return true;
    }


    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        String username = useridInput.getText();
        String password = passwordInput.getText();

        boolean checkUser = DBLogin.loginAttempt(username, password);

        Log.auditLog(username, checkUser);

        if(checkUser){


            Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Customers");
            stage.setScene(scene);
            stage.show();

        }
        else{
            alert("Error", "Incorrect credentials", "Please try again");
            return;

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Locale locale = Locale.getDefault();
        resourceBundle = ResourceBundle.getBundle("languageResource/Languages");
        LabelLocation.setText(ZoneId.systemDefault().toString());
        title.setText(resourceBundle.getString("title"));
        loginBtn.setText(resourceBundle.getString("loginBtn"));
        passwordInput.setPromptText(resourceBundle.getString("passwordInput"));
        useridInput.setPromptText(resourceBundle.getString("useridInput"));



    }

}
