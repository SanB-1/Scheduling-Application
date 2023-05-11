package Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CustomerDAO {

    public static int insert(int cID, int cDivID, String cName, String cAddress, String cZip, String cPhone,
                             Date cCreatedOn, String cCreatedBy, Timestamp cLastUpdate, String cLastUpdateBy)
            throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ? ,? ,?, ?)";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, cID);
        ps.setInt(2, cDivID);
        ps.setString(3, cName);
        ps.setString(3, cAddress);
        ps.setString(4, cZip);
        ps.setString(5, cPhone);
        ps.setDate(6, cCreatedOn);
        ps.setString(7, cCreatedBy);
        ps.setTimestamp(8, cLastUpdate);
        ps.setString(9, cLastUpdateBy);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 1){
            System.out.println("Inserted Successfully.");
        } else{
            System.out.println("Insert Failed.");
        }
        return rowsAffected;
    }
}
