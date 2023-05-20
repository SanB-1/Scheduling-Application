package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class for the Users DAO.
 */
public class UsersDAO {

    /**
     * Returns a list of all User IDs in the users table.
     * @return
     * @throws SQLException
     */
    public static ArrayList<String> userIDList() throws SQLException {
        ArrayList<String> uList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("User_ID");
            uList.add(id);
        }
        return uList;
    }
}
