package DBAccess;

import Database.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {
    public static Integer getContactId(String contactName){
        try {
            String sql = "SELECT Contact_ID "
                    + "FROM contacts"
                    + "WHERE Contact_Name = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, contactName);

            int id = 0;

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("Contact_ID");

            }
            return id;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
