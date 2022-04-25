package DBAccess;

import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBUser {

    public static int getUserId(){
        int userId = 0;
        try {
            String sql = "SELECT User_ID FROM user";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                userId = rs.getInt("userId");

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userId;
    }

    public static ObservableList<User> getAllUsers(){
        ObservableList<User> uList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String username = rs.getString("User_Name");
                String password = rs.getString("Password");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String author = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdateAuthor = rs.getString("Last_Updated_By");


                User U = new User(userId, username, password, createDate, author, lastUpdate, lastUpdateAuthor);

                uList.add(U);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }return uList;
    }


    public static void getUsers(String Username, String Password) {

        ObservableList<User> users = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM user WHERE userName='" + Username + "' AND password='" + Password + "'";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");

                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);

                users.add(newUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
