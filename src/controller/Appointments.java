package controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
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
    private ComboBox CustId;
    @FXML
    private ComboBox UserId;
    @FXML
    private Button BackBtn;
    @FXML
    private ComboBox MonthList;
    @FXML
    private ComboBox TypeList;
    @FXML
    private Button CreateReportBtn;
    @FXML
    private ComboBox ContactList;
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
    private ComboBox Contact;
    @FXML
    private ComboBox Type;
    @FXML
    private DatePicker Date;
    @FXML
    private TextField StartTime;
    @FXML
    private TextField EndTime;

    ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){



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


    public void cancelClick(ActionEvent actionEvent) {
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

    public void contactScheduleClick(ActionEvent actionEvent) {
    }



    public void backClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }
}
