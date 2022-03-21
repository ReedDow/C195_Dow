package DBAccess;

import Database.DBConnection;;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import java.sql.*;

public class DBCustomers {

    public static ObservableList<Customer> getAllCustomers(){

        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM customers";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String division = rs.getString("Division");
                int divisionId = rs.getInt("Division_ID");
                String country = rs.getString("Country");

                Customer C = new Customer(customerId, customerName, address, postalCode, phone, division, divisionId, country);

                customerList.add(C);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }


}
