package DBAccess;

import Database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions {

    /**Selects groups of first_level_divisions based on selected country ID
     * @return*/
    public static ObservableList<Division> getSelectedDivisions(String country) {
        ObservableList<Division> divList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT first_level_divisions.Division "
                    + "FROM first_level_divisions, countries "
                    + "WHERE first_level_divisions.Country_ID = countries.Country_ID "
                    + "AND countries.Country = \"" + country + "\"";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()){
                String division = rs.getString("Division");

                Division D = new Division( division);

                divList.add(D);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            //Logger.getLogger(ComboBoxExampleController.class.getName()).log(Level.SEVERE, null, ex);
        }return divList;

    }

}
