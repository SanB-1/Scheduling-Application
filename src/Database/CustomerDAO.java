package Database;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;


public abstract class CustomerDAO {

    public static void insert(String cName, String cAddress, String cZip, String cPhone,
                             Timestamp cCreatedOn, String cCreatedBy, Timestamp cLastUpdate, String cLastUpdateBy,
                             int cDivID)
            throws SQLException {

        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By," +
                " Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);

        ps.setString(1, cName);
        ps.setString(2, cAddress);
        ps.setString(3, cZip);
        ps.setString(4, cPhone);
        ps.setTimestamp(5, cCreatedOn);
        ps.setString(6, cCreatedBy);
        ps.setTimestamp(7, cLastUpdate);
        ps.setString(8, cLastUpdateBy);
        ps.setInt(9, cDivID);
        ps.executeUpdate();
    }

    public static ObservableList<Customer> select() throws SQLException {
        ObservableList<Customer> cList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String id = rs.getString("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp cDate = rs.getTimestamp("Create_Date");
            String cBy = rs.getString("Created_By");
            Timestamp lUpdate = rs.getTimestamp("Last_Update");
            String lUpdateBy = rs.getString("Last_Updated_By");
            Integer dID = rs.getInt("Division_ID");
            Customer customer = new Customer(Integer.parseInt(id), name, address, zip, phone, cDate, cBy, lUpdate,
                    lUpdateBy, dID);
            cList.add(customer);
        }
        return cList;

    }

    public static int update(int cID, int cDivID, String cName, String cAddress, String cZip, String cPhone,
                             Date cCreatedOn, String cCreatedBy, Timestamp cLastUpdate, String cLastUpdateBy)
        throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ? ,? ,?, ?) WHERE " +
                "Customer_ID = ?";
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
        ps.setInt(10, cID);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 1){
            System.out.println("Update Successfully.");
        } else{
            System.out.println("Update Failed.");
        }
        return rowsAffected;
    }

    public static int delete(int cID) throws SQLException{
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, cID);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected >= 1){
            System.out.println("Deletion Successful.");
        } else{
            System.out.println("Deletion Failed.");
        }
        return rowsAffected;
    }
}
