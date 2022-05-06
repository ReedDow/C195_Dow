package DBAccess;

import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;
import model.Customer;

import java.sql.*;
import java.time.LocalDate;
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
            String sql = "SELECT appointments.*, User_Name, Contact_Name, " +
                    "Customer_Name FROM appointments, customers, contacts, " +
                    "users WHERE appointments.Customer_ID=customers.Customer_ID " +
                    "and appointments.Contact_ID=contacts.Contact_ID and " +
                    "appointments.User_ID=users.User_ID";

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

    public static ObservableList<String> getApptStarts() throws SQLException {

        ObservableList<String> cList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Start FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                cList.add(rs.getString("Start"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return cList;
    }

    public static ObservableList<Appointment> getApptTimeRange(LocalDateTime startRange, LocalDateTime timeDescription)
            throws SQLException {

        String sql = "SELECT * FROM appointments a " +
                "INNER JOIN customers c " +
                "ON a.Customer_ID = c.Customer_ID " +
                "INNER JOIN users u "  +
                "ON a.User_ID = u.User_ID " +
                "LEFT OUTER JOIN contacts t " +
                "ON a.Contact_ID = t.Contact_ID WHERE" +
                " Start between ? AND ?";

        ObservableList<Appointment> apptTimeRange = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        String startRangeString = startRange.format(formatter);
        String endRangeString = timeDescription.format(formatter);

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

public static void newAppointment(String title, String description, String location, String contact, String type, LocalDateTime start, LocalDateTime end,  int custId, int userId, int contactId){
        try{
            String sql = "INSERT INTO appointments Values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setTimestamp(7,Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, DBLogin.getCurrentUser().getUsername());
            ps.setTimestamp(9,Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(10, DBLogin.getCurrentUser().getUsername());
            ps.setInt(11, custId);
            ps.setInt(12, userId);
            ps.setInt(13, contactId);

            ps.executeUpdate();
        }
       catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyAppointment(int appointmentId, String title, String description,  String location,  String type, LocalDateTime start, LocalDateTime end, int custId, int userId, int contactId){
        try{
            String sql = "UPDATE appointments " +
                    "Set Title= ?, Description= ?, Location= ?, Type= ?, Start= ?, End= ?, Create_Date= ?, Created_By= ?, " +
                    "Last_Update= ?, Last_Updated_By= ?, Customer_ID= ?, User_ID= ?, Contact_ID = ? " +
                    "WHERE Appointment_ID = " + appointmentId;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, DBLogin.getCurrentUser().getUsername());
            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(10, DBLogin.getCurrentUser().getUsername());
            ps.setString(11, String.valueOf(custId));
            ps.setString(12, String.valueOf(userId));
            ps.setString(13, String.valueOf(contactId));

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean deleteAppointment(Integer selectedAppointmentId) throws SQLException {

        try {
            String sql = "DELETE FROM appointments "
                    + "WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, selectedAppointmentId);

            ps.executeUpdate();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static ObservableList<Appointment> getAppointmentOverlap( Integer inputApptID) throws SQLException {
        ObservableList<Appointment> filteredAppts = FXCollections.observableArrayList();

        String sql = "SELECT * " +
                " FROM appointments AS a " +
                " INNER JOIN users AS u " +
                " ON a.User_ID = u.User_ID " +
                " INNER JOIN contacts AS o " +
                " ON a.Contact_ID = o.Contact_ID " +
                " INNER JOIN customers AS c " +
                " ON a.Customer_ID = c.Customer_ID " +
                " WHERE a.Appointment_ID <> " + inputApptID;


        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {

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
            String customerName = rs.getString("Customer_Name");
            Integer customerId = rs.getInt("Customer_ID");
            Integer userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            Integer contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");

            Appointment A = new Appointment(appointmentId, title, description, location, type, start, end, createTime, author, lastUpdate, updateAuthor, customerId, customerName, contactId, contactName, userId, userName);
            filteredAppts.add(A);
        }

        ps.close();
        return filteredAppts;
    }

        public static ObservableList<String> getAllTypes() throws SQLException {

        ObservableList<String> cList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT DISTINCT Type FROM appointments";

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

    public static Integer getContactID(String contact) {

        try {

            String sql = "SELECT Contact_ID "
                    + "FROM contacts "
                    + "WHERE Contact_Name = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, contact);

            int conId = 0;

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                conId = rs.getInt("Contact_ID");
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
            String sql = "SELECT User_ID FROM users";

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
            String sql = "SELECT Customer_ID FROM customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                cList.add(rs.getString("Customer_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return cList;
    }

    public static ObservableList<Customer> getCustomerLocation(String customerName){
        ObservableList<Customer> cList = FXCollections.observableArrayList();
        try{
            String sql = "select * "
                    + "from first_level_divisions f "
                    + " inner join customers c "
                    + "on f.Division_ID = c.Division_ID "
                    + "inner join countries o "
                    + "on f.Country_ID = o.Country_ID "
                    + "where Customer_Name = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, customerName);

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

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return cList;
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
                    + "INNER JOIN contacts AS o "
                    + "ON a.Contact_ID = o.Contact_ID "
                    + "WHERE o.Contact_Name = ?" ;


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

    public static ObservableList<String> getType() throws SQLException {
        ObservableList<String> tList = FXCollections.observableArrayList();

        String sql = "SELECT type, count(Type) AS Total "
                + "FROM appointments GROUP BY Type";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while(rs.next()){
            tList.add(rs.getString("Type"));
            tList.add(rs.getString("Total"));
        }
        return tList;
    }

    public static ObservableList<String> getMonth() throws SQLException {
        ObservableList<String> mList = FXCollections.observableArrayList();

        String sql = "SELECT MONTHNAME(Start) AS Month, "
                + "COUNT(MONTH(Start)) AS Total FROM appointments GROUP BY Month";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while(rs.next()){
            mList.add(rs.getString("Month"));
            mList.add(rs.getString("Total"));
        }
        return mList;
    }

}
