package DBAccess;

import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    public static int getUserId(String user){
        int userId = 0;
        try {
            String sql = "SELECT * FROM user WHERE userName = ?";
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

//    public static ObservableList<User> getUserNames(){
//        ObservableList<User> usernames = FXCollections.observableArrayList();
//        try {
//            String sql = "SELECT * FROM user WHERE username = ?";
//            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery(sql);
//
//            while (rs.next()) {
//                String username = rs.getString("username");
//
//                usernames.add(username);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return usernames;
//    }

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
