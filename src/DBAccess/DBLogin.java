package DBAccess;

import Database.JDBC;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBLogin {

    private static User currentUser;

    private static User getCurrentUser(){
        return currentUser;
    }

    public static Boolean loginAttempt(String username, String password) {

        try{
            String sql = "SELECT * FROM user WHERE User_Name='" + username + "' AND Password='" + password + "'";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            if(rs.next()){
                currentUser = new User();
                currentUser.setUsername(rs.getString("User_Name"));
                ps.close();

                return Boolean.TRUE;

            } else {
                return Boolean.FALSE;
            }


        }catch (Exception e) {
        e.printStackTrace();
    }
        return true;

    }
}
