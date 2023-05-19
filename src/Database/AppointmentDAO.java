package Database;

import Controllers.Login;
import Model.Appointment;
import Utils.Helpers;
import com.sun.jdi.IntegerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AppointmentDAO {

    public static void insert(String title, String description, String location, String type, Timestamp start,
                              Timestamp end, Timestamp createDate, String createBy, Timestamp lastUpdate,
                              String lastUpdateBy, String customerID, String userID, String contactID)
            throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End," +
                " Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Helpers.systemToUTC(start));
        ps.setTimestamp(6, Helpers.systemToUTC(end));
        ps.setTimestamp(7, Helpers.systemToUTC(createDate));
        ps.setString(8, createBy);
        ps.setTimestamp(9, Helpers.systemToUTC(lastUpdate));
        ps.setString(10, lastUpdateBy);
        ps.setInt(11, Integer.parseInt(customerID));
        ps.setInt(12, Integer.parseInt(userID));
        ps.setInt(13, Integer.parseInt(contactID));
        ps.executeUpdate();
    }

    public static ObservableList<Appointment> select() throws SQLException {
        ObservableList<Appointment> app = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Integer id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            Integer customerID = rs.getInt("Customer_ID");
            Integer userID = rs.getInt("User_ID");
            Integer contactID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(id, title, description, location, type, Helpers.utcToSystem(start)
                    , Helpers.utcToSystem(end), Helpers.utcToSystem(createDate), createdBy,
                    Helpers.utcToSystem(lastUpdate), lastUpdateBy, customerID, userID, contactID);
            app.add(appointment);
        }
        return app;
    }

    public static void update(String title, String description, String location, String type, Timestamp start,
                              Timestamp end, Timestamp createDate, String createBy, Timestamp lastUpdate,
                              String lastUpdateBy, String customerID, String userID, String contactID, String ID)
            throws SQLException {
        String sql = "UPDATE appointments " +
                "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?," +
                " Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?" +
                " WHERE Appointment_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Helpers.systemToUTC(start));
        ps.setTimestamp(6, Helpers.systemToUTC(end));
        ps.setTimestamp(7, Helpers.systemToUTC(createDate));
        ps.setString(8, createBy);
        ps.setTimestamp(9, Helpers.systemToUTC(lastUpdate));
        ps.setString(10, lastUpdateBy);
        ps.setInt(11, Integer.parseInt(customerID));
        ps.setInt(12, Integer.parseInt(userID));
        ps.setInt(13, Integer.parseInt(contactID));
        ps.setInt(14, Integer.parseInt(ID));
        ps.executeUpdate();
    }

    public static int delete(int aID) throws SQLException{
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, aID);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected >= 1){
            System.out.println("Deletion Successful.");
        } else{
            System.out.println("Deletion Failed.");
        }
        return rowsAffected;
    }

    public static ObservableList<Appointment> appByCustID(int cID) throws SQLException {
        ObservableList<Appointment> app = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1,cID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Integer id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            Integer customerID = rs.getInt("Customer_ID");
            Integer userID = rs.getInt("User_ID");
            Integer contactID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(id, title, description, location, type, Helpers.utcToSystem(start)
                    , Helpers.utcToSystem(end), Helpers.utcToSystem(createDate), createdBy,
                    Helpers.utcToSystem(lastUpdate), lastUpdateBy, customerID, userID, contactID);
            app.add(appointment);
        }
        return app;
    }

    public static boolean overlap(Timestamp start, int aID, int cID) throws SQLException {
        String sql = "SELECT Title, Appointment_ID, Customer_ID FROM appointments " +
                "WHERE ? BETWEEN Start AND End " +
                "AND ? <> Appointment_ID " +
                "AND ? = Customer_ID;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setTimestamp(1, Helpers.systemToUTC(start));
        ps.setInt(3, cID);
        ps.setInt(2, aID);
        ResultSet rs = ps.executeQuery();
        System.out.println(Helpers.systemToUTC(start));
        System.out.println(cID);
        System.out.println(aID);
        return rs.next();
    }

    public static ArrayList<Appointment> within15() throws SQLException {
        String sql = "SELECT * FROM appointments \n" +
                "WHERE TIMESTAMPDIFF(MINUTE, ?, Start) <= 15\n" +
                "AND User_ID = ?\n" +
                "AND ? <= Start;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setTimestamp(1, Helpers.systemToUTC(new Timestamp(System.currentTimeMillis())));
        ps.setInt(2, Login.currentUserID);
        ps.setTimestamp(3, Helpers.systemToUTC(new Timestamp(System.currentTimeMillis())));
        ResultSet rs = ps.executeQuery();
        ArrayList<Appointment> list = new ArrayList<>();
        while (rs.next()){
            Integer id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            Integer customerID = rs.getInt("Customer_ID");
            Integer userID = rs.getInt("User_ID");
            Integer contactID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(id, title, description, location, type, Helpers.utcToSystem(start)
                    , Helpers.utcToSystem(end), Helpers.utcToSystem(createDate), createdBy,
                    Helpers.utcToSystem(lastUpdate), lastUpdateBy, customerID, userID, contactID);
            list.add(appointment);
        }
        return list;
    }
}
