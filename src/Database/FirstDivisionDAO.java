package Database;

import Model.FirstLevelDivision;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FirstDivisionDAO {

    public static ArrayList<FirstLevelDivision> divList() throws SQLException {

        ArrayList<FirstLevelDivision> cList = new ArrayList<>();
        String sql = "SELECT * FROM first_level_divisions";
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

    public static ArrayList<FirstLevelDivision> divsWhere(Integer div) throws SQLException {
        ArrayList<FirstLevelDivision> cList = new ArrayList<>();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
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

}
