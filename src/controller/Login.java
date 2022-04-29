package controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import DBAccess.DBLogin;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Log;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;


/** This class initializes the Login form and is the start of the app as specified in main.java*/
public class Login implements Initializable {

    public Label location;
    @FXML
    private Label apptLabel;

    @FXML
    private Label title;

    @FXML
    private Label UpcomingAppointments;

    @FXML
    private Label LabelLocation;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField useridInput;

    /**This method finds the user's location and initializes text language based on location*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            appointmentAlert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Locale locale = Locale.getDefault();
        resourceBundle = ResourceBundle.getBundle("languageResource/login", locale.getDefault());
        location.setText(ZoneId.systemDefault().toString());
        LabelLocation.setText(resourceBundle.getString("labelLocation"));
        title.setText(resourceBundle.getString("title"));
        loginBtn.setText(resourceBundle.getString("loginBtn"));
        passwordInput.setPromptText(resourceBundle.getString("passwordInput"));
        useridInput.setPromptText(resourceBundle.getString("useridInput"));
        apptLabel.setText(resourceBundle.getString("apptLabel"));
    }

    /**This method sets an information alert that can be customized in subsequent methods.
     * @param aTitle The title of the alert.
     * @param aHeader the header message of the alert.
     * @param aContent the main message of the alert. */
    public boolean alert(String aTitle, String aHeader, String aContent) {
        Locale locale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("languageResource/login", locale.getDefault());
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle(resourceBundle.getString("aTitle"));
        alert1.setHeaderText(resourceBundle.getString("aHeader"));
        alert1.setContentText(resourceBundle.getString("aContent"));
        alert1.showAndWait();
        return true;
    }

    /**This method checks for any appointments within 15 minutes and displays any relevant appointments in the UI*/
    public void appointmentAlert() throws SQLException {
        Locale locale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("languageResource/login", locale.getDefault());
        ObservableList<Appointment> appointments = DBAppointments.getAllAppointments();

        LocalDateTime add15 = LocalDateTime.now().plusMinutes(15);

        for (Appointment appointment : appointments) {
            LocalDateTime apptStart = appointment.getStart();
            if (apptStart.isBefore(add15) && apptStart.isAfter(LocalDateTime.now())) {
                int apptId = appointment.getAppointmentId();
                LocalDateTime time = apptStart;

                UpcomingAppointments.setText("Id: " + apptId + " " + "Time: " + time);
                System.out.println("Id: " + apptId + " " + "Date/Time: " + time);
            }
            else { UpcomingAppointments.setText("No appointments within 15 minutes.");
            }
        }
    }

/**This method checks the input username and password against the users table in the DB to allow login.
 * If correct credentials are provided the method loads the Customers view.
 * If incorrect credentials are provided an alert window shows an error.
 * A .txt log file is updated on every login attempt, indicating username and attempt success/fail */
    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        String username = useridInput.getText();
        String password = passwordInput.getText();

        boolean checkUser = DBLogin.loginAttempt(username, password);

        Log.loginActivity(username, checkUser);

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

        }
    }
}
