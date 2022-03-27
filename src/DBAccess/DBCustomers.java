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
//        PreparedStatement ps = JDBC.makeConnection().prepareStatement(sql);
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


    public static ObservableList<Customer> getAllCustomers(){

        ObservableList<Customer> clist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM customers.*, first_level_divisions.Division, first_level_divisions.COUNTRY_ID, countries.Country FROM customers, first_level_divisions, countries WHERE customers.Division_ID=first_level_divisions.Division_ID and first_level_divisions.COUNTRY_ID = countries.Country_ID";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");


                Customer C = new Customer(customerId, name, address, postalCode, phone, divisionId);

                clist.add(C);
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
            //ps.setString(5, newCustomer.getDivision());
            ps.setInt(6, newCustomer.getDivisionId());
            //ps.setString(7, newCustomer.getCountry());


        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
