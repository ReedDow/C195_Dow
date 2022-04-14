package controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;

public class Appointments implements Initializable {

    @FXML
    private DatePicker StartDate;
    @FXML
    private DatePicker EndDate;
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
    private TextField StartTime;
    @FXML
    private TextField EndTime;

    private static String selectedContact;

    ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LocalDateTime localDate = LocalDateTime.now();
        System.out.println(localDate);
        LocalDateTime month = localDate.plus(1, ChronoUnit.MONTHS);
        System.out.println(month);


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

    public void radioWeekClick(ActionEvent actionEvent) throws SQLException {
        LocalDateTime localDate = LocalDateTime.now();
        LocalDateTime week = localDate.plus(1, ChronoUnit.WEEKS);
        ApptTable.setItems(DBAppointments.getApptTimeRange(LocalDateTime.now(), week));

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

    public void radioMonthClick(ActionEvent actionEvent) throws SQLException {
        LocalDateTime localDate = LocalDateTime.now();
        System.out.println(localDate);
        LocalDateTime month = localDate.plus(1, ChronoUnit.MONTHS);
        System.out.println(month);
        ApptTable.setItems(DBAppointments.getApptTimeRange(localDate, month));

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

    public void radioAllClick(ActionEvent actionEvent) {

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

    public void addClick(ActionEvent actionEvent) throws IOException {
        boolean added = false;

        String title = Title.getText();
        String description = Description.getText();
        String location = Location.getText();
        String startTime = StartTime.getText();
        String endTime = EndTime.getText();
        String startDate  = String.valueOf(StartDate.getValue());
        String endDate = String.valueOf(EndDate.getValue());
        String type = String.valueOf(Type.getValue());
        int custId = Integer.parseInt(CustId.getValue());
        int userId = Integer.parseInt(UserId.getValue());

//        if (title.isEmpty() || description.isEmpty() || location.isEmpty() || startDate.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || endDate.isEmpty() || type.isEmpty() || custId.isEmpty() || userId.isEmpty() ){
//
//            alert("Error", "Invalid input", "All fields must be filled");
//        }
//
//        else {
//
//            DBCustomers.newCustomer(country, name, address, division, postalCode, phone, divisionId);

//
//            added = true;
//        }

        if (added) {

            Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Main form");
            stage.setScene(scene);
            stage.show();
        }
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
