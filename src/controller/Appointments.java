package controller;

import DBAccess.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Appointments implements Initializable {

    @FXML
    private TableView<Appointment> ApptTable;
    @FXML
    private TableColumn<Appointment, Integer> ApptIdCol;
    @FXML
    private TableColumn<Appointment, String> TitleCol;
    @FXML
    private TableColumn<Appointment, String> DescCol;
    @FXML
    private TableColumn<Appointment, String> LocCol;
    @FXML
    private TableColumn<Appointment, String> ContactCol;
    @FXML
    private TableColumn<Appointment, String> TypeCol;
    @FXML
    private TableColumn<Appointment, String> StartCol;
    @FXML
    private TableColumn<Appointment, String> EndCol;
    @FXML
    private TableColumn<Appointment, String> CustIdCol;
    @FXML
    private TableColumn<Appointment, String> UserIdCol;
    @FXML
    private Button CancelBtn;
    @FXML
    private Button DeleteBtn;
    @FXML
    private RadioButton WeekAppts;
    @FXML
    private RadioButton MonthAppts;
    @FXML
    private RadioButton AllAppts;
    @FXML
    private Button AddAppt;
    @FXML
    private Button UpdateAppt;
    @FXML
    private ComboBox<String> CustId;
    @FXML
    private ComboBox<String> UserId;
    @FXML
    private Button BackBtn;
    @FXML
    private ComboBox MonthList;
    @FXML
    private ComboBox<String> TypeList;
    @FXML
    private Button CreateReportBtn;
    @FXML
    private ComboBox<String> ContactList;
    @FXML
    private Button GenerateContactSchedule;
    @FXML
    private TextField ApptId;
    @FXML
    private TextField Title;
    @FXML
    private TextField Description;
    @FXML
    private TextField Location;
    @FXML
    private ComboBox<String> Contact;
    @FXML
    private ComboBox<String> Type;
    @FXML
    private DatePicker Date;
    @FXML
    private TextField StartTime;
    @FXML
    private TextField EndTime;

    private static String selectedContact;

    ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            Contact.setItems(DBAppointments.getAllContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ContactList.setItems(DBAppointments.getAllContacts());
        } catch (SQLException e) {
            e.printStackTrace();
       }

        try {
            Type.setItems(DBAppointments.getAllTypes());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            TypeList.setItems(DBAppointments.getAllTypes());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            CustId.setItems(DBAppointments.getAllCustomerIds());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            UserId.setItems(DBAppointments.getAllUserIds());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ApptTable.setItems(DBAppointments.getAllAppointments());

        ApptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        UserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    static boolean confirm(String title, String header, String content){
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle(title);
        alert2.setHeaderText(header);
        alert2.setContentText(content);

        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK)
            return true;
        else return false;
    }

    /**This method redirects to the previous page after a confirmation dialog returns true upon "OK" click */
    public void cancelClick(ActionEvent actionEvent) throws IOException {
        if(confirm("Warning - Leaving page", "Any unsaved data will be lost", "Would you like to leave this page?")){
            Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Customers");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void deleteClick(ActionEvent actionEvent) {
    }

    public void radioWeekClick(ActionEvent actionEvent) {
    }

    public void radioMonthClick(ActionEvent actionEvent) {
    }

    public void radioAllClick(ActionEvent actionEvent) {
    }

    public void addClick(ActionEvent actionEvent) {
    }

    public void updateClick(ActionEvent actionEvent) {
    }

    public void createReportClick(ActionEvent actionEvent) {
    }

    public static String getSelectedContact(){return selectedContact; }

    public void contactScheduleClick(ActionEvent actionEvent) throws IOException {

        selectedContact = String.valueOf(ContactList.getValue());

        Parent root = FXMLLoader.load(getClass().getResource("/view/ContactSchedule.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    /**This method redirects to the previous page after a confirmation dialog returns true upon "Yes" click */
    public void backClick(ActionEvent actionEvent) throws IOException {
        if(confirm("Warning - Leaving page", "Any unsaved data will be lost", "Would you like to leave this page?")){
            Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Customers");
            stage.setScene(scene);
            stage.show();
        }
    }


}
