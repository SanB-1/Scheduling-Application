package Database;

import Model.Country;
import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

/**
 * Class for the Country DAO.
 */
public abstract class CountryDAO {

    /**
     * Returns an Observable List containing all the Countries in the countries table.
     * @return
     * @throws SQLException
     */
    public static ObservableList<Country> countryList() throws SQLException {

        ObservableList<Country> cList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("Country_ID");
            String name = rs.getString("Country");
            Country country = new Country(Integer.parseInt(id), name);
            cList.add(country);
        }
        return cList;
    }

    /**
     * Returns the Country ID corresponding to the selected Country name.
     * @param country
     * @return
     * @throws SQLException
     */
    public static int countryToID(String country) throws SQLException {
        ObservableList<FirstLevelDivision> cList = FXCollections.observableArrayList();
        String sql = "SELECT Country_ID from countries WHERE Country = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, country);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("Country_ID");
    }

    /**
     * Returns the Country Name corresponding to the selected Country ID.
     * @param id
     * @return
     * @throws SQLException
     */
    public static String IDtoCountry(int id) throws SQLException {
        String sql = "SELECT Country from countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("Country");
    }

}
