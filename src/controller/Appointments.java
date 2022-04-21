package controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
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
import java.time.*;
import java.time.format.DateTimeFormatter;
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

        ToggleGroup group = new ToggleGroup();
        WeekAppts.setToggleGroup(group);
        MonthAppts.setToggleGroup(group);
        AllAppts.setToggleGroup(group);

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

    /**This method sets an information alert that can be customized in subsequent methods.
     *  * @param title The title of the alert. * @param header the header message of the alert.
     *  * @param content the main message of the alert. */

    public boolean alert(String title, String header, String content) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle(title);    alert1.setHeaderText(header);
        alert1.setContentText(content);    alert1.showAndWait();
        return true;}

    /**This method sets a confirmation alert that can be customized in subsequent methods.
     * * @param title The title of the alert. * @param header the header message of the alert.
     * * @param content the main message of the alert. * @return returns true if user clicks "OK" and false if user clicks "Cancel".*/
    static boolean confirm(String title, String header, String content){
         Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
         alert2.setTitle(title);
         alert2.setHeaderText(header);
         alert2.setContentText(content);
         Optional<ButtonType> result = alert2.showAndWait();
         if (result.get() == ButtonType.OK)
             return true;
         else return false;}


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
    /** Adds a new appointment into the DB and populates onto appointment table.
     * Formats the time/date input from String into LocalDateTime
     * Checks for empty values and alerts user*/
    public void addClick(ActionEvent actionEvent) throws IOException {
        boolean added = false;

        String title = Title.getText();
        String description = Description.getText();
        String location = Location.getText();
        String contact = String.valueOf(Contact.getValue());
        String startTime = StartTime.getText();
        String endTime = EndTime.getText();
        LocalDate startDate  = StartDate.getValue();
        LocalDate endDate = EndDate.getValue();
        String type = String.valueOf(Type.getValue());
        Integer custId = Integer.parseInt(CustId.getValue());
        Integer userId = Integer.parseInt(UserId.getValue());

        String endStr = (endDate + " " + endTime);
        String startStr = (startDate  + " " + startTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(startStr, formatter);
        LocalDateTime end = LocalDateTime.parse(endStr, formatter);

       if (title.isEmpty() || description.isEmpty() || location.isEmpty() || startDate == null || startTime.isEmpty() || endTime.isEmpty() || endDate == null || type.isEmpty() || custId == null || userId == null ){

            alert("Error", "Invalid input", "All fields must be filled");
        }

        else {
           DBAppointments.newAppointment(title, description, location, contact, type, start, end, custId, userId);
            added = true;
        }

        if (added) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Main form");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void updateClick(ActionEvent actionEvent) throws SQLException {
        Appointment appointment = ApptTable.getSelectionModel().getSelectedItem();

//        ZonedDateTime start = appointment.getStart().atZone(ZoneOffset.UTC);
//
//        ZonedDateTime localStart = new ZonedDateTime(ZoneId.systemDefault());
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        String startLocal = localStart.format(formatter);



        if(ApptTable.getSelectionModel().isEmpty()) {
            alert("Error", "No appointment selected", "Please select appointment to update");
            return;
        }
        else {
            //ApptId.setText(appointment.getAppointmentId().toString());
            Title.setText(appointment.getTitle());
            Description.setText(appointment.getDescription());
            Location.setText(appointment.getLocation());
            Contact.setItems(DBAppointments.getAllContacts());
            Contact.getSelectionModel().select(appointment.getContact());
            Type.setItems(DBAppointments.getAllTypes());
            Type.getSelectionModel().select(appointment.getType());
            StartDate.setValue(appointment.getStart().toLocalDate());
            EndDate.setValue(appointment.getEnd().toLocalDate());
//            StartTime.setText(startLocal);
//            EndTime.setText(endLocal);

        }
    }

    public void createReportClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MonthType.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
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
