package controller;

import DBAccess.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**This class initializes the appointments form */
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
    private Button CreateReportBtn;
    @FXML
    private ComboBox<String> ContactList;
    @FXML
    private ComboBox<String> CustomerList;
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
    private static String selectedCustomer;

    ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

    /**This method populates the appointment table and all combo-boxes.
     * It initializes the toggle group for radio buttons
     * It uses a lambda expression to disable weekend on the date selectors for start and end date.
     * The benefit of a lambda expression here is that it can be done within the initialize method, rather than a whole separate method. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        StartDate.setDayCellFactory(p -> new DateCell() {
            public void updateItem(LocalDate StartDate, boolean disable) {
                super.updateItem(StartDate, false);
                setDisable(
                    StartDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    StartDate.getDayOfWeek() == DayOfWeek.SUNDAY);
            }
        });
        EndDate.setDayCellFactory(p -> new DateCell() {
            public void updateItem(LocalDate EndDate, boolean disable) {
                super.updateItem(EndDate, false);
                setDisable(
                    EndDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    EndDate.getDayOfWeek() == DayOfWeek.SUNDAY);
            }
        });


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
            CustomerList.setItems(DBCustomers.getCustomerNames());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            Type.setItems(DBAppointments.getAllTypes());
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
        alert1.setTitle(title);
        alert1.setHeaderText(header);
        alert1.setContentText(content);
        alert1.showAndWait();
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

    /**This method deletes the selected customer and associated appointments
     * Error message displays if no customer selected.
     * Confirmation message displays before after clicking delete. */
    public void onDeleteClick(ActionEvent actionEvent) throws SQLException {
        if(ApptTable.getSelectionModel().isEmpty()){
            alert("Error", "No appointment selected", "Please select appointment to delete");
            return;
        }
        Appointment appointment = ApptTable.getSelectionModel().getSelectedItem();


        if(confirm("Warning", "Appointment with ID : " + appointment.getAppointmentId() + " Type: " + appointment.getType() + " selected for deletion", "Would you like to delete selected appointment? ")) {

            Appointment appointmentId = ApptTable.getSelectionModel().getSelectedItem();

            DBAppointments.deleteAppointment(appointmentId.getAppointmentId());

           ApptTable.setItems(DBAppointments.getAllAppointments());
        }
        else{alert("Error", "Unable to delete appointment", "Please try again");
            return;}
    }
    /**Sets radio button to This Week, and updates the appointment table accordingly*/

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
    /**Sets radio button to This Month, and updates the appointment table accordingly*/

    public void radioMonthClick(ActionEvent actionEvent) throws SQLException {
        LocalDateTime localDate = LocalDateTime.now();
        LocalDateTime month = localDate.plus(1, ChronoUnit.MONTHS);
        ApptTable.setItems(DBAppointments.getApptTimeRange(LocalDateTime.now(), month));

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
/**Sets radio button to All Appointments, and updates the appointment table accordingly*/
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
     * Checks for empty values and appointments outside of business hours and alerts the user*/
    public void addClick(ActionEvent actionEvent) throws IOException, SQLException {
        boolean added = false;

        LocalDateTime start = null;
        LocalDateTime end = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        String startTime = null;
        String endTime = null;
        int custId = 0;
        int userId= 0;
        int contactId = 0;


        String title = Title.getText();
        String description = Description.getText();
        String location = Location.getText();
        String contact = String.valueOf(Contact.getValue());
        String type = String.valueOf(Type.getValue());

        contactId = DBAppointments.getContactID(contact);

        if (CustId.getValue()!=null) custId = Integer.parseInt(CustId.getValue());

        if(UserId.getValue()!=null) userId = Integer.parseInt(UserId.getValue());

        try {  startTime = StartTime.getText();
            endTime = EndTime.getText();
            startDate  = StartDate.getValue();
            endDate = EndDate.getValue();

            String endStr = (endDate + " " + endTime);
            String startStr = (startDate  + " " + startTime);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            start = LocalDateTime.parse(startStr, formatter);
            end = LocalDateTime.parse(endStr, formatter);
        }

        catch(DateTimeParseException error) {

            alert("Error", "Invalid time input", "Time fields requires exact format hh:mm");
            return;
        }

        if (title.isEmpty() || description.isEmpty() || location.isEmpty() || startDate == null || startTime.isEmpty() || endTime.isEmpty() || endDate == null || type.isEmpty() || custId == 0 || userId == 0) {

            alert("Error", "Invalid input", "All fields must be filled");

            return;
        }

        Boolean overlapCheck = checkOverlap(start, end, custId);
        Boolean hoursCheck = checkBusinessHours(start, end, startDate, endDate);

        if (!overlapCheck) {
            alert("Error", "Overlapping appointments", "Please schedule appointment when there is no other appointment scheduled");
            return;
        }
        if (!hoursCheck) {
            alert("Error", "Appointment outside of business hours", "Please create an appointment between 8am-10pm");
            return;
        }

        else {
           DBAppointments.newAppointment(title, description, location, contact, type, start, end, custId, userId, contactId);
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

/**This method populates the fields with appointment information from the selected appointment.
 * If no appointment is selected an error is shown describing the issue. */
    public void updateClick(ActionEvent actionEvent) throws SQLException {
        Appointment appointment = ApptTable.getSelectionModel().getSelectedItem();

        if(ApptTable.getSelectionModel().isEmpty()) {
            alert("Error", "No appointment selected", "Please select appointment to update");
            return;
        }

        ZonedDateTime start = appointment.getStart().atZone(ZoneOffset.UTC);
        ZonedDateTime end = appointment.getEnd().atZone(ZoneOffset.UTC);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime lbStart = start.toLocalDateTime();
        LocalDateTime lbEnd = end.toLocalDateTime();
        String startLocal = lbStart.format(formatter);
        String endLocal = lbEnd.format(formatter);


            ApptId.setText(Integer.toString(appointment.getAppointmentId()));
            Title.setText(appointment.getTitle());
            Description.setText(appointment.getDescription());
            Location.setText(appointment.getLocation());
            Contact.getSelectionModel().select(appointment.getContact());

            Type.getSelectionModel().select(appointment.getType());
            StartDate.setValue(appointment.getStart().toLocalDate());
            EndDate.setValue(appointment.getEnd().toLocalDate());
            StartTime.setText(startLocal);
            EndTime.setText(endLocal);
            CustId.getSelectionModel().select(String.valueOf((appointment.getCustomerId())));
            UserId.getSelectionModel().select(String.valueOf(appointment.getUserId()));

    }

/**This method saves the modified data in the text fields to the DB and repopulates the table with updated information.
 * It checks for empty fields and overlapping appointments and creates an alert if necessary.*/
    public void onSaveClick(ActionEvent actionEvent) throws IOException, SQLException {

        LocalDateTime start = null;
        LocalDateTime end = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        String startTime = null;
        String endTime = null;

        int custId = 0;
        int userId = 0;
        int contactId;


        if(confirm("Warning", "Appointment selected for modification", "Would you like to modify selected appointment?")) {

            boolean added = false;

            int apptId = Integer.parseInt(ApptId.getText());
            String title = Title.getText();
            String description = Description.getText();
            String location = Location.getText();
            String contact = String.valueOf(Contact.getValue());
            String type = String.valueOf(Type.getValue());

            if (CustId.getValue()!=null) custId = Integer.parseInt(CustId.getValue());

            if(UserId.getValue()!=null) userId = Integer.parseInt(UserId.getValue());

            contactId = DBAppointments.getContactID(contact);

            try {
                startTime = StartTime.getText();
                endTime = EndTime.getText();
                startDate = StartDate.getValue();
                endDate = EndDate.getValue();

                String endStr = (endDate + " " + endTime);
                String startStr = (startDate + " " + startTime);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                start = LocalDateTime.parse(startStr, formatter);
                end = LocalDateTime.parse(endStr, formatter);

            } catch (DateTimeParseException error) {

                alert("Error", "Invalid time input", "Time fields requires exact format hh:mm");
                return;
            }

            Boolean overlapCheck = checkOverlap(start, end, custId);
            Boolean hoursCheck = checkBusinessHours(start, end, startDate, endDate);

            if (!overlapCheck) {
                alert("Error", "Overlapping appointments", "Please schedule appointment when there is no other appointment scheduled");
                return;
            }

            if (!hoursCheck) {
                alert("Error", "Appointment outside of business hours", "Please create an appointment between 8am-10pm");
                return;
            }

            if (title.isEmpty() || description.isEmpty() || location.isEmpty() || startDate == null || contact.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || endDate == null || type.isEmpty() || custId == 0 || userId == 0) {

                alert("Error", "Invalid input", "All fields must be filled");
            }

            else {
                DBAppointments.modifyAppointment(apptId, title, description, location, type, start, end, custId, userId, contactId);
                System.out.println("Got to modifyAppointment DB");
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
    }

    /**This method checks for overlapping appointments by comparing the new appointment with appointments in the database.
     * * @param start the LocalDateTime start of the new appointment.
     * * @param end the LocalDateTime end of the new appointment.
     * * @return returns true if no conditions are met and false if any condition is met */
    public Boolean checkOverlap(LocalDateTime start, LocalDateTime end, Integer inputCustomerId) throws SQLException {
        ObservableList<Appointment> appointment = DBAppointments.getAppointmentOverlap(inputCustomerId);

        if (appointment.isEmpty()) {
            return true;
        }
            for (Appointment currentAppointments : appointment) {

                LocalDateTime overlapStart = currentAppointments.getStart();
                LocalDateTime overlapEnd = currentAppointments.getEnd();

                if (overlapStart.equals(start)) {

                    return false;

                }
                if (overlapStart.isBefore(start) && overlapEnd.isAfter(start)) {

                    return false;
                }
                if (overlapStart.isBefore(end) && overlapStart.isAfter(start)) {

                    return false;
                } else {

                   continue;
                }
            }
        return true;
    }

    public Boolean checkBusinessHours(LocalDateTime start, LocalDateTime end, LocalDate startDate, LocalDate endDate) {


        ZonedDateTime startZone = ZonedDateTime.of(start, ZoneId.systemDefault());
        ZonedDateTime endZone = ZonedDateTime.of(end, ZoneId.systemDefault());

        ZonedDateTime startBusinessDay = ZonedDateTime.of(startDate, LocalTime.of(8, 0),
                ZoneId.of("America/New_York"));
        ZonedDateTime endBusinessDay = ZonedDateTime.of(endDate, LocalTime.of(22, 0),
                ZoneId.of("America/New_York"));


        if (startZone.isBefore(startBusinessDay) || startZone.isAfter(endBusinessDay) ||
                endZone.isBefore(startBusinessDay) || endZone.isAfter(endBusinessDay) ||
                startZone.isAfter(endZone)) {
            return false;
        }
        else {
            return true;
        }

    }

/**Upon click this method creates a report in an alert box with data showing total appointments by type and month*/
    public void createReportClick(ActionEvent actionEvent) throws IOException {
        try {
            ObservableList<String> reportMonth = DBAppointments.getMonth();

            ObservableList<String> reportType = DBAppointments.getType();

            alert("Report Generated","Total Appointments by Month and Type", "Month: " + reportMonth + "\n" + "Type: " + reportType);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getSelectedContact(){
        return selectedContact;
    }
/**This method sets the selected contact to the variable selectedContact for use in ContactSchedule controller*/
    public void contactScheduleClick(ActionEvent actionEvent) throws IOException {

        selectedContact = String.valueOf(ContactList.getValue());

        Parent root = FXMLLoader.load(getClass().getResource("/view/ContactSchedule.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Contact Schedule");
        stage.setScene(scene);
        stage.show();
    }

    public static String getSelectedCustomer(){return selectedCustomer;
    }

    /**This method sets the selected customer to the variable selectedCustomer for use in CustomerLocation controller*/
    public void customerLocationClick(ActionEvent actionEvent) throws IOException {
        selectedCustomer = String.valueOf(CustomerList.getValue());

        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerLocation.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Location");
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
