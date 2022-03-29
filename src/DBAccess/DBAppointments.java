package DBAccess;

import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import java.sql.*;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBAppointments {

    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static Appointment getAppointment(int aId) {

        try {
            String sql = "SELECT appointments.*, User_Name, Contact_Name, Customer_Name FROM appointments, customers, contacts, users WHERE appointments.Customer_ID=customers.Customer_ID and appointments.Contact_ID=contacts.Contact_ID and appointments.User_ID=users.User_ID and appointments.Appointments_ID=" + aId;
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime  start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createTime = rs.getTimestamp("Create_Date").toLocalDateTime();
                String author = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String updateAuthor = rs.getString("Last_Updated_By");
                Integer customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                Integer contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Integer userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");

                Appointment A = new Appointment(appointmentId, title, description, location, type, start, end,createTime, author, lastUpdate, updateAuthor, customerId, customerName, contactId, contactName, userId, userName);
                return A;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ObservableList getAllAppointments(){


        ObservableList<Appointment> aList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT appointments.*, User_Name, Contact_Name, Customer_Name FROM appointments, customers, contacts, users WHERE appointments.Customer_ID=customers.Customer_ID and appointments.Contact_ID=contacts.Contact_ID and appointments.User_ID=users.User_ID";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime createTime = rs.getTimestamp("Create_Date").toLocalDateTime();
                String author = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String updateAuthor = rs.getString("Last_Updated_By");
                Integer customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                Integer contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                Integer userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");

                Appointment A = new Appointment(appointmentId, title, description, location, type, start, end, createTime, author, lastUpdate, updateAuthor, customerId, customerName, contactId, contactName, userId, userName);

                aList.add(A);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return aList;
    }

public static void newAppointment(Appointment newAppointment){
        try{
            String sql = "INSERT INTO appointments Values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, newAppointment.getTitle());
            ps.setString(2, newAppointment.getDescription());
            ps.setString(3, newAppointment.getLocation());
            ps.setString(4, newAppointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(newAppointment.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(newAppointment.getEnd()));
            ps.setTimestamp(7, Timestamp.valueOf(newAppointment.getCreatedTime()));
            ps.setString(8, newAppointment.getAuthor());
            ps.setTimestamp(9, Timestamp.valueOf(newAppointment.getLastUpdate()));
            ps.setString(10, newAppointment.getLastUpdateAuthor());
            ps.setString(11, String.valueOf(newAppointment.getContactId()));
            ps.setString(12, String.valueOf(newAppointment.getUserId()));
            ps.setString(13, String.valueOf(newAppointment.getCustomerId()));
        }
       catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAppointment(Appointment newAppointment){
        try{
            String sql = "UPDATE appointments Set Title=?, Description=?, Location=?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, newAppointment.getTitle());
            ps.setString(2, newAppointment.getDescription());
            ps.setString(3, newAppointment.getLocation());
            ps.setString(4, newAppointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(newAppointment.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(newAppointment.getEnd()));
            ps.setTimestamp(7, Timestamp.valueOf(newAppointment.getCreatedTime()));
            ps.setString(8, newAppointment.getAuthor());
            ps.setTimestamp(9, Timestamp.valueOf(newAppointment.getLastUpdate()));
            ps.setString(10, newAppointment.getLastUpdateAuthor());
            ps.setString(11, String.valueOf(newAppointment.getContactId()));
            ps.setString(12, String.valueOf(newAppointment.getUserId()));
            ps.setString(13, String.valueOf(newAppointment.getCustomerId()));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
