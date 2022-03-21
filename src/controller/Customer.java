package controller;

import DBAccess.DBCustomers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Customer implements Initializable{
    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn CustIdCol;
    @FXML
    private TableColumn CustNameCol;
    @FXML
    private TableColumn CustAddressCol;
    @FXML
    private TableColumn CustPostCol;
    @FXML
    private TableColumn CustPhoneCol;
    @FXML
    private TextField CustID;
    @FXML
    private TextField CustNmae;
    @FXML
    private TextField CustAddress;
    @FXML
    private TextField CustPostal;
    @FXML
    private TextField CustPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

//        ObservableList<model.Customer> customerList = DBCustomers.getAllCustomers();
//        for(model.Customer C : customerList){
//            System.out.println("Country id : " + C.);
//        }
        System.out.println("Initialized!");

    }


    public void onDeleteClick(ActionEvent actionEvent) {
    }

    public void onSaveClick(ActionEvent actionEvent) {
    }

    public void onCancelClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void onAddClick(ActionEvent actionEvent) {
    }

    public void onModifyClick(ActionEvent actionEvent) {
    }

    public void scheduleClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointment.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }
}
