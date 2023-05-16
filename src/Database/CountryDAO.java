package Database;

import Model.Country;
import java.sql.*;
import java.util.ArrayList;

public abstract class CountryDAO {

    public static ArrayList<Country> countryList() throws SQLException {

        ArrayList<Country> cList = new ArrayList<>();
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
}
