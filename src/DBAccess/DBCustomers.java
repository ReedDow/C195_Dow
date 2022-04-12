package DBAccess;


import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.time.LocalDateTime;


public class DBCustomers {

    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public static Customer getCustomer(int cId){

        try {

            String sql = "SELECT customers.*, first_level_divisions.Division, first_level_divisions.COUNTRY_ID, countries.Country FROM customers, first_level_divisions, countries WHERE customers.Division_ID=first_level_divisions.Division_ID and first_level_divisions.COUNTRY_ID = countries.Country_ID and customer.Customer_ID=" + cId;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setName(rs.getString("customerName"));
                ps.close();
                return customer;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
    public static void newCustomer(String country, String name, String address, String division, String postalCode, String phone, int divisionId){
        try{
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, "
            + "Created_By, Last_Update, Last_Updated_By, Division_ID) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString (3, postalCode);
            ps.setString(4, phone);
            ps.setTimestamp(5,Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, DBLogin.getCurrentUser().getUsername());
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, DBLogin.getCurrentUser().getUsername());;
            ps.setInt(9, divisionId);


            ps.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void modifyCustomer(Customer customer){


    }

    public static Boolean deleteCustomer(Integer selectedCustomerId) throws SQLException {

        try {
        String sql = "DELETE FROM customers "
        + "WHERE Customer_ID = ?";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ps.setInt(1, selectedCustomerId);

        ps.executeUpdate();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
