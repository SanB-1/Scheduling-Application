package Database;

import Model.Customer;
import Utils.Helpers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class for the Customer DAO.
 */
public abstract class CustomerDAO {

    /**
     * Inserts a new Customer into the customers table.
     * @param cName
     * @param cAddress
     * @param cZip
     * @param cPhone
     * @param cCreatedOn
     * @param cCreatedBy
     * @param cLastUpdate
     * @param cLastUpdateBy
     * @param cDivID
     * @throws SQLException
     */
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
        ps.setTimestamp(5, Helpers.systemToUTC(cCreatedOn));
        ps.setString(6, cCreatedBy);
        ps.setTimestamp(7, Helpers.systemToUTC(cLastUpdate));
        ps.setString(8, cLastUpdateBy);
        ps.setInt(9, cDivID);
        ps.executeUpdate();
    }

    /**
     * Selects all Customers from the customers table.
     * @return
     * @throws SQLException
     */
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
            int dID = rs.getInt("Division_ID");
            Customer customer = new Customer(Integer.parseInt(id), name, address, zip, phone, Helpers.utcToSystem(cDate)
                    , cBy, Helpers.utcToSystem(lUpdate), lUpdateBy, dID);
            cList.add(customer);
        }
        return cList;

    }

    /**
     * Updates the selected Appointment from the appointments table.
     * @param cID
     * @param cDivID
     * @param cName
     * @param cAddress
     * @param cZip
     * @param cPhone
     * @param cCreatedOn
     * @param cCreatedBy
     * @param cLastUpdate
     * @param cLastUpdateBy
     * @throws SQLException
     */
    public static void update(int cID, int cDivID, String cName, String cAddress, String cZip, String cPhone,
                             Timestamp cCreatedOn, String cCreatedBy, Timestamp cLastUpdate, String cLastUpdateBy)
        throws SQLException {
        String sql = "UPDATE customers " +
                "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, " +
                "Last_Update = ?, Last_Updated_By = ?, Division_ID = ?" +
                " WHERE Customer_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, cName);
        ps.setString(2, cAddress);
        ps.setString(3, cZip);
        ps.setString(4, cPhone);
        ps.setTimestamp(5, Helpers.systemToUTC(cCreatedOn));
        ps.setString(6, cCreatedBy);
        ps.setTimestamp(7, Helpers.systemToUTC(cLastUpdate));
        ps.setString(8, cLastUpdateBy);
        ps.setInt(9, cDivID);
        ps.setInt(10, cID);
        ps.executeUpdate();
    }

    /**
     * Deletes the record corresponding to the selected Customer.
     * @param cID
     * @throws SQLException
     */
    public static void delete(int cID) throws SQLException{
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, cID);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected >= 1){
            System.out.println("Deletion Successful.");
        } else{
            System.out.println("Deletion Failed.");
        }
    }

    /**
     * Returns a list of all the Customer IDs.
     */
    public static ArrayList<String> customerIDList() throws SQLException {
        ArrayList<String> cList = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("Customer_ID");
            cList.add(id);
        }
        return cList;
    }

    /**
     * Returns the Customer ID for the selected Customer.
     * @param name
     * @return
     * @throws SQLException
     */
    public static Integer nameToID(String name) throws SQLException {
        int id = 0;
        String sql = "SELECT Customer_ID FROM customers WHERE Customer_Name = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
             id = rs.getInt("Customer_ID");
        }
        return id;
    }
}
