package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Appointment {

    @FXML
    private TableView ApptTable;
    @FXML
    private TableColumn ApptIdCol;
    @FXML
    private TableColumn TitleCol;
    @FXML
    private TableColumn DescCol;
    @FXML
    private TableColumn LocCol;
    @FXML
    private TableColumn ContactCol;
    @FXML
    private TableColumn TypeCol;
    @FXML
    private TableColumn StartCol;
    @FXML
    private TableColumn EndCol;
    @FXML
    private TableColumn CustIdCol;
    @FXML
    private TableColumn UserIdCol;
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
