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

public class DBCountries {

    /**this method returns the country  selected by countryId */
    public static Country getCountry(){
        try {
            String sql = "SELECT * FROM countries WHERE countryId = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String author = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdateAuthor = rs.getString("Last_Updated_By");


                Country C = new Country(countryId, countryName, createDate, author, lastUpdate, lastUpdateAuthor);
                return C;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }

    /**this method returns all countries from the database */
    public static ObservableList <Country> getAllCountries(){

        ObservableList<Country> cList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String author = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdateAuthor = rs.getString("Last_Updated_By");


                Country C = new Country(countryId, countryName, createDate, author, lastUpdate, lastUpdateAuthor);

                cList.add(C);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cList;
    }

    public static ObservableList<String> getCountryNames() throws SQLException {

        ObservableList<String> cList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country FROM countries";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                cList.add(rs.getString("Country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return cList;
    }


    public static void modifyCountry(Country country){
        try {
            String sql = "UPDATE countries " +
                    "SET country = ?, lastUpdate = NOW(), lastUpdateBy = ?" +
                    "WHERE countryId = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, country.getCountryName());
            ps.setString(2, User.getUsername());
            ps.setInt(3, country.getCountryId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
