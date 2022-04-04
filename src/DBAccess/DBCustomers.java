package DBAccess;


import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;
import java.time.LocalDateTime;


public class DBCustomers {

    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public static Customer getCustomer(int cId){

        try {
        String sql = "SELECT customers.*, first_level_divisions.Division, first_level_divisions.COUNTRY_ID, countries.Country FROM customers, first_level_divisions, countries WHERE customers.Division_ID=first_level_divisions.Division_ID and first_level_divisions.COUNTRY_ID = countries.Country_ID and customer.Customer_ID=" + cId;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime time = rs.getTimestamp("Create_Date").toLocalDateTime();
                String author = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdateAuthor = rs.getString("Last_Updated_By");
                String division = rs.getString("Division");
                int divisionId = rs.getInt("Division_ID");
                String country = rs.getString("Country");
                int countryId = rs.getInt("country_ID");

                Customer C = new Customer(customerId, name, address, postalCode, phone, time, author, lastUpdate, lastUpdateAuthor, division, divisionId, country, countryId);
                return C;
            }
    } catch (SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
    }
        return null;
    }


    public static ObservableList<Customer> getAllCustomers(){

        ObservableList<Customer> cList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT customers.*, first_level_divisions.Division, first_level_divisions.COUNTRY_ID, countries.Country FROM customers, first_level_divisions, countries WHERE customers.Division_ID=first_level_divisions.Division_ID and first_level_divisions.COUNTRY_ID = countries.Country_ID";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime time = rs.getTimestamp("Create_Date").toLocalDateTime();
                String author = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdateAuthor = rs.getString("Last_Updated_By");
                String division = rs.getString("Division");
                int divisionId = rs.getInt("Division_ID");
                String country = rs.getString("Country");
                int countryId = rs.getInt("country_ID");


                Customer C = new Customer(customerId, name, address, postalCode, phone, time, author, lastUpdate, lastUpdateAuthor, division, divisionId, country, countryId);

                cList.add(C);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cList;
    }

    /**Add new customer method*/
    public static void newCustomer(Customer newCustomer){
        try{
            String sql = "INSERT INTO customers Values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, newCustomer.getName());
            ps.setString(2, newCustomer.getAddress());
            ps.setString(3, newCustomer.getPostalCode());
            ps.setString(4, newCustomer.getPhone());
            ps.setTimestamp(5, Timestamp.valueOf(newCustomer.getTime()));
            ps.setString(6, newCustomer.getAuthor());
            ps.setTimestamp(7, Timestamp.valueOf(newCustomer.getLastUpdate()));
            ps.setString(8, newCustomer.getLastUpdateAuthor());
            ps.setInt(9, newCustomer.getDivisionId());
            ps.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void modifyCustomer(Customer customer){

    }

    public static boolean deleteCustomer(Customer selectedCustomer) {
        for (Customer c : allCustomers) {
            if (c.getCustomerId() == selectedCustomer.getCustomerId()) {
                allCustomers.remove(c);
                return true;
            }
        }
        return false;
    }


}
