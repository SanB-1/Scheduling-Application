package Database;

import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class for the First Division DAO.
 */
public class FirstDivisionDAO {

    /**
     * Returns all the data for all the first level divisions.
     * @return
     * @throws SQLException
     */
    public static ArrayList<FirstLevelDivision> divList() throws SQLException {

        ArrayList<FirstLevelDivision> cList = new ArrayList<>();
        String sql = "SELECT * FROM first_level_divisions;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("Division_ID");
            String cID = rs.getString("Country_ID");
            String name = rs.getString("Division");
            FirstLevelDivision div = new FirstLevelDivision(Integer.parseInt(id), Integer.parseInt(cID), name);
            cList.add(div);
        }
        return cList;
    }

    /**
     * Returns a list of all the first level divisions corresponding to the provided Division ID.
     * @param div
     * @return
     * @throws SQLException
     */
    public static ArrayList<FirstLevelDivision> divsWhere(Integer div) throws SQLException {
        ArrayList<FirstLevelDivision> cList = new ArrayList<>();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, div);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("Division_ID");
            String cID = rs.getString("Country_ID");
            String name = rs.getString("Division");
            FirstLevelDivision division = new FirstLevelDivision(Integer.parseInt(id), Integer.parseInt(cID), name);
            cList.add(division);
        }
        return cList;
    }

    /**
     * Returns the first level division corresponding to the selected Division name.
     * @param div
     * @return
     * @throws SQLException
     */
    public static int divToID(String div) throws SQLException {
        ObservableList<FirstLevelDivision> cList = FXCollections.observableArrayList();
        String sql = "SELECT Division_ID from first_level_divisions WHERE Division = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, div);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer name = rs.getInt("Division_ID");
        return name;
    }

    /**
     * Returns the Division name corresponding to the selected Division ID.
     * @param id
     * @return
     * @throws SQLException
     */
    public static String getDivisionyByID(String id) throws SQLException {
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(id));
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("Division");
    }

    /**
     * Returns the Country ID corresponding to the Division ID.
     * @param id
     * @return
     * @throws SQLException
     */
    public static int divIDtoCountryID(int id) throws SQLException {
        String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("Country_ID");
    }
}
