package DBAccess;

import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;

import java.sql.*;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DBAppointments {

    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static Appointment getAppointment(int aId) {

        try {
            String sql =
//                    "SELECT appointments.*, User_Name, Contact_Name, Customer_Name FROM appointments, customers, contacts, users WHERE appointments.Customer_ID=customers.Customer_ID and appointments.Contact_ID=contacts.Contact_ID and appointments.User_ID=users.User_ID and appointments.Appointments_ID=" + aId;

                    "SELECT * FROM appointments a" +
                            "INNER JOIN customers c " +
                            "WHERE a.Customer_ID = c.Customer_ID" +
                            " AND a.Appointment_ID = " + aId;
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

    public static ObservableList<Appointment> getApptTimeRange(ZonedDateTime startRange, ZonedDateTime endRange)
            throws SQLException {

        String sql = "SELECT * FROM appointments a " +
                "INNER JOIN customers c" +
                "WHERE a.Customer_ID = c.Customer_ID " +
                "INNER JOIN users u "  +
                "ON a.User_ID = u.User_ID " +
                "LEFT OUTER JOIN contacts c " +
                "ON a.Contact_ID = c.Contact_ID WHERE" +
                " Start between ? AND ?";

        ObservableList<Appointment> apptTimeRange = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        String startRangeString = startRange.format(formatter);
        String endRangeString = endRange.format(formatter);

        ps.setString(1, startRangeString);
        ps.setString(2, endRangeString);

        ResultSet rs = ps.executeQuery();

        while( rs.next() ) {
            Integer appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            LocalDateTime createTime = rs.getTimestamp("Create_Date").toLocalDateTime();
            String author = rs.getString("Created_by");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String updateAuthor = rs.getString("Last_Updated_By");
            Integer customerId = rs.getInt("Customer_ID");
            Integer userId = rs.getInt("User_ID");
            Integer contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String userName = rs.getString("User_Name");
            String customerName = rs.getString("Customer_Name");

            Appointment appointment = new Appointment(
                    appointmentId, title, description, location, type, start, end, createTime, author, lastUpdate, updateAuthor, customerId, customerName, contactId, contactName, userId, userName
            );
            apptTimeRange.add(appointment);
        }

        return apptTimeRange;

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

    public static Boolean deleteAppointment(Integer selectedCustomerId) throws SQLException {

        try {
            String sql = "DELETE FROM appointments "
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

    public static ObservableList<String> getAllTypes() throws SQLException {

        ObservableList<String> cList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Type FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                cList.add(rs.getString("Type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return cList;
    }

    public static ObservableList<String> getAllContacts() throws SQLException {

        ObservableList<String> cList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_Name FROM contacts";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                cList.add(rs.getString("Contact_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return cList;
    }

    public static Integer getContactID(String contactName) {

        try {
            String sql = "SELECT Contact_ID "
                    + "FROM contacts "
                    + "WHERE Contact_Name = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, contactName);

            int conId = 0;

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                conId = rs.getInt("Contact_Name");

            }
            return conId;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<String> getAllUserIds() throws SQLException {

        ObservableList<String> uList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT User_ID FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                uList.add(rs.getString("User_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return uList;
    }

    public static ObservableList<String> getAllCustomerIds() throws SQLException {

        ObservableList<String> cList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Customer_ID FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                cList.add(rs.getString("Customer_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return cList;
    }


    public static ObservableList<Appointment> getContactSchedule(String contactName) {
        ObservableList<Appointment> aList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * "
                    + "FROM appointments AS a "
                    + "INNER JOIN customers AS c "
                    + "ON a.Customer_ID = c.Customer_ID "
                    + "INNER JOIN users AS u "
                    + "ON a.User_ID = u.User_ID "
                    + "INNER JOIN contacts "
                    + "WHERE Contact_Name = ?";


            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, contactName);

            ResultSet rs = ps.executeQuery();

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
//                String contactName = rs.getString("Contact_Name");
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


}
