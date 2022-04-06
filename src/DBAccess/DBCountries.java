package DBAccess;

import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    /**this method adds a country to db */
    public static void addCountry(Country newCountry) {
        try {
            String sql = "INSERT INTO countries VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);


            ps.setString(1, newCountry.getCountryName());
            ps.setTimestamp(2, Timestamp.valueOf(newCountry.getCreateDate()));
            ps.setString(3, newCountry.getAuthor());
            ps.setTimestamp(4, Timestamp.valueOf(newCountry.getLastUpdate()));
            ps.setString(5, newCountry.getLastUpdateAuthor());
            ps.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
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
