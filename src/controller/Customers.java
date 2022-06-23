package controller;

import DBAccess.DBAppointments;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Country;
import model.Division;
import model.Customer;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class initializes the Customers form, with the customers table and fields*/
public class Customers implements Initializable{

    public Button modify;
    @FXML
    private TextField CustSearch;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> CustIdCol;
    @FXML
    private TableColumn<Customer, String> CustNameCol;
    @FXML
    private TableColumn<Customer, String> CustAddressCol;
    @FXML
    private  TableColumn<Customer, Integer> CustDivCol;
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
    @FXML
    private ComboBox<Division> CustState;
    @FXML
    private ComboBox<Country> CustCountry;
    @FXML


    /**This method populates State/Province/Division combo-box when Country has been chosen.*/
    public void initializeDivision(){

        String selectedCountry = String.valueOf(CustCountry.getValue());

        CustState.setItems(DBDivisions.getSelectedDivisions(selectedCountry));
    }

    /**This method populates the Customers table and comboboxes*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        /**Populate Country combo-box from DB upon page initialization*/
        CustCountry.setItems(DBCountries.getAllCountries());

        /**Populate customer table from DB upon page initialization*/
        customerTable.setItems(DBCustomers.getAllCustomers());

        CustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CustNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        CustAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        CustDivCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
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

    /**This method deletes the selected customer and associated appointments
     * Error message displays if no customer selected.
     * Confirmation message displays before after clicking delete. */
    public void onDeleteClick(ActionEvent actionEvent) throws SQLException {
        if(customerTable.getSelectionModel().isEmpty()){
            alert("Error", "No customer selected", "Please select customer to delete");
            return;
        }
        if(confirm("Warning", "Customer selected for deletion", "Would you like to delete selected customer and their appointments?")) {

            Customer selectedCustomerId = customerTable.getSelectionModel().getSelectedItem();

            DBAppointments.deleteAppointment(selectedCustomerId.getCustomerId());

            DBCustomers.deleteCustomer(selectedCustomerId.getCustomerId());

            customerTable.setItems(DBCustomers.getAllCustomers());
        }
        else{alert("Error", "Unable to delete customer", "Please try again");
            return;}
    }

    /**This method redirects to the previous page after a confirmation dialog returns true upon "OK" click */
    public void onCancelClick(ActionEvent actionEvent) throws IOException {
        if(confirm("Warning - Leaving page", "Any unsaved data will be lost", "Would you like to leave this page?")){
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**This method compares input name or id with customers and displays matches.
     * Displays alert if text field is empty.
     * Regex allows for partial name match to display
     * After search, if text field is empty, it repopulates with full list of parts.*/
    public void customerSearchButton(ActionEvent actionEvent) {

        ObservableList<Customer> allCustomers = DBCustomers.getAllCustomers();
        ObservableList<Customer> customerSearchResults = FXCollections.observableArrayList();
        String search = CustSearch.getText();

        for (Customer customer : allCustomers) {
            if (String.valueOf(customer.getCustomerId()).contains(search) ||
                    String.valueOf(customer.getName()).matches(("(?i:.*" + search + ".*)"))) {
                customerSearchResults.add(customer);
            }
        }
        customerTable.setItems(customerSearchResults);

        if(customerSearchResults.isEmpty()) {
            alert("No customer found", "Your search for " + '"' + search + '"' + " was not found", "Please try another search");
            customerTable.refresh();
        }
    }

    /** This method allows the user to add a new customer by inputting data in the fields and clicking "Add"
     * The method checks for empty fields and alerts the user otherwise.*/
    public void onAddClick(ActionEvent actionEvent) throws IOException, SQLException {

        boolean added = false;

        String name = CustName.getText();
        String address = CustAddress.getText();
        String division = String.valueOf(CustState.getValue());
        String country = String.valueOf(CustCountry.getValue());
        String postalCode = CustPostal.getText();
        String phone = CustPhone.getText();

        int divisionId = DBDivisions.getDivisionID(division);

        if (name.isEmpty() || address.isEmpty() || division.isEmpty() || country.isEmpty() || postalCode.isEmpty() || phone.isEmpty()) {

            alert("Error", "Invalid input", "All fields must be filled");
        }

        else {
            DBCustomers.newCustomer( name, address, postalCode, phone, divisionId);

            added = true;
            }

        if (added) {

            Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Main form");
            stage.setScene(scene);
            stage.show();
            }
    }

    /** This method allows the user to modify a customer in the Customer table, and auto-populates the fields
     * */
    public void onModifyClick(ActionEvent actionEvent) throws IOException, SQLException {

        Customer customer = customerTable.getSelectionModel().getSelectedItem();

        if(customerTable.getSelectionModel().isEmpty()) {
            alert("Error", "No customer selected", "Please select customer to modify");
            return;
        }
        else {
            CustID.setText(customer.getCustomerId().toString());
            CustName.setText(customer.getName());
            CustAddress.setText(customer.getAddress());
            CustPostal.setText(customer.getPostalCode());
            CustPhone.setText(customer.getPhone());

            CustCountry.setItems(DBCountries.getAllCountries());

            Country country = null;

            for (Country c : DBCountries.getAllCountries()){
                if(c.getCountryName().equals(customer.getCountry())){
                    country = c;
                }
            }
            CustCountry.getSelectionModel().select(country);

            CustState.setItems(DBDivisions.getSelectedDivisions(customer.getCountry()));
            Division division = null;


            for (Division d : DBDivisions.getAllDivisions()){
                if(d.getDivisionName().equals(customer.getDivision())){
                    division = d;
                }
            }
            CustState.getSelectionModel().select(division);
        }
    }

/**This method saves the modified customer data in the fields
 * it checks for empty fields and alerts the user
 * The table is updated immediately with the new information*/
    public void onSaveClick(ActionEvent actionEvent) throws IOException {

        if(confirm("Warning", "Customer selected for modification", "Would you like to modify selected customer?")) {

        boolean added = false;
        int customerId = Integer.parseInt(CustID.getText());
        String name = CustName.getText();
        String address = CustAddress.getText();
        String division = String.valueOf(CustState.getValue());
        String country = String.valueOf(CustCountry.getValue());
        String postalCode = CustPostal.getText();
        String phone = CustPhone.getText();

        int divisionId = DBDivisions.getDivisionID(division);

        if (name.isEmpty() || address.isEmpty() || division.isEmpty() || country.isEmpty() || postalCode.isEmpty() || phone.isEmpty()) {
            alert("Error", "Invalid input", "All fields must be filled");
        }

        else {
            DBCustomers.modifyCustomer(customerId, name, address, postalCode, phone, divisionId);
            added = true;
        }

        if (added) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Main form");
            stage.setScene(scene);
            stage.show();
        }
        }
    }
/**This method directs the view to the Appointments page.*/
    public void scheduleClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }
}
