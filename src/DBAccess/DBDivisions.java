package DBAccess;

import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBDivisions {

    /**Selects groups of first_level_divisions based on selected country ID
     * @return*/
    public static ObservableList<Division> getSelectedDivisions(String country) {
        ObservableList<Division> dList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT first_level_divisions.Division, first_level_divisions.Division_ID, first_level_divisions.Create_Date, first_level_divisions.Created_By, first_level_divisions.Last_Update, first_level_divisions.Last_Updated_By, first_level_divisions.Country_ID "
                    + "FROM first_level_divisions, countries "
                    + "WHERE first_level_divisions.Country_ID = countries.Country_ID "
                    + "AND countries.Country = '" + country + "'";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String author = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdateAuthor = rs.getString("Last_Updated_By");
                int countryId = rs.getInt("country_ID");

                Division D = new Division(divisionId, divisionName, createDate, author, lastUpdate, lastUpdateAuthor, countryId);
                dList.add(D) ;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }return dList;

    }

    public static ObservableList<Division> getAllDivisions(){
         ObservableList<Division> dList = FXCollections.observableArrayList();
         try{
             String sql="SELECT first_level_divisions.*, countries.Country FROM first_level_divisions JOIN countries on first_level_divisions.Country_ID=countries.Country_ID)";

             PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery();

             while(rs.next()){
                 int divisionId = rs.getInt("Division_ID");
                 String divisionName = rs.getString("Division");
                 LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                 String author = rs.getString("Created_By");
                 LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                 String lastUpdateAuthor = rs.getString("Last_Updated_By");
                 int countryId = rs.getInt("country_ID");

                 Division D = new Division(divisionId, divisionName, createDate, author, lastUpdate, lastUpdateAuthor, countryId);

                 dList.add(D);
             }
         }catch (SQLException e) {
             e.printStackTrace();
         }
        return dList;
    }

    public static Integer getDivisionID(String division) {

         try {
             String sql = "SELECT Division_ID "
                     + "FROM first_level_divisions "
                     + "WHERE Division = ?";

             PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

             ps.setString(1, division);

             int divId = 0;

             ResultSet rs = ps.executeQuery();

             while (rs.next()) {
                 divId = rs.getInt("Division_ID");

             }
             return divId;
         }catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
    }
}
