package DBAccess;


import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;


public class DBCustomers {

    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

//    public static Customer getCustomer(int customerId){
//     try {
//        String sql = "SELECT * FROM customer WHERE customerId='" + customerId + "'";
//        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
//        ResultSet rs = ps.executeQuery(sql);
//
//        if (rs.next()) {
//            Customer C = new Customer();
//            C.setName(rs.getString("customerName"));
//            ps.close();
//            return C;
//        }
//    } catch (SQLException e) {
//        System.out.println("SQLException: " + e.getMessage());
//    }
//        return null;
//    }


    public static ObservableList<controller.Customer> getAllCustomers(){

        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

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
        return null;
    }

    /**Add new customer method*/
    public static void newCustomer(Customer newCustomer){
        try{
            String sql = "INSERT INTO customer (customerName, address, postalCode, phone, division, divisionId, country, createDate, createdBy, lastUpdate, lastUpdatedBy" + "Values (?, ?, ?, ?, ?, ?, ?, Now(), ?, Now(), ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, newCustomer.getName());
            ps.setString(2, newCustomer.getAddress());
            ps.setString(3, newCustomer.getPostalCode());
            ps.setString(4, newCustomer.getPhone());
            ps.setString(5, newCustomer.getDivision());
            ps.setInt(6, newCustomer.getDivisionId());
            ps.setString(7, newCustomer.getCountry());


        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
