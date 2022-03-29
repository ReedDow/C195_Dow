package controller;

import DBAccess.DBCustomers;
import javafx.collections.FXCollections;
import model.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;




import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Customers implements Initializable{



    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> CustIdCol;
    @FXML
    private TableColumn<Customer, String> CustNameCol;
    @FXML
    private TableColumn<Customer, String> CustAddressCol;
    @FXML
    private  TableColumn<Customer, Integer> CustDiv;
    @FXML
    private TableColumn<Customer, String> CustPostCol;
    @FXML
    private TableColumn<Customer, String> CustPhoneCol;
    @FXML
    private TextField CustID;
    @FXML
    private TextField CustName;
    @FXML
    private TextField CustAddress;
    @FXML
    private TextField CustPostal;
    @FXML
    private TextField CustPhone;
//    @FXML
//    private ComboBox<Division> CustCountry;
//    @FXML
//    public ComboBox<Country> CustState;


    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        customerTable.setItems(DBCustomers.getAllCustomers());

        CustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CustNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        CustAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        CustDiv.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        CustPostCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        CustPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

    }

    /**This method sets an information alert that can be customized in subsequent methods.
     * @param title The title of the alert.
     * @param header the header message of the alert.
     * @param content the main message of the alert. */
    public boolean alert(String title, String header, String content) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle(title);
        alert1.setHeaderText(header);
        alert1.setContentText(content);
        alert1.showAndWait();
        return true;
    }

    /**This method sets a confirmation alert that can be customized in subsequent methods.
     * @param title The title of the alert.
     * @param header the header message of the alert.
     * @param content the main message of the alert.
     * @return returns true if user clicks "OK" and false if user clicks "Cancel". */
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


    public void onDeleteClick(ActionEvent actionEvent) {
        if(customerTable.getSelectionModel().isEmpty()){
            alert("Error", "No customer selected", "Please select customer to delete");
            return;
        }
        if(confirm("Warning", "Customer selected for deletion", "Would you like to delete selected customer?")) {

            Customer customerToDelete = customerTable.getSelectionModel().getSelectedItem();
            DBCustomers.deleteCustomer(customerToDelete);
            customerTable.refresh();
        }
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

    public void onSaveClick(ActionEvent actionEvent) {

    }

    public void scheduleClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }
}
