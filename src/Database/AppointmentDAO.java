package Database;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AppointmentDAO {

    public static void insert(String title, String description, String location, String type, Timestamp start,
                              Timestamp end, Timestamp createDate, String createBy, Timestamp lastUpdate,
                              Timestamp lastUpdateBy, Integer customerID, Integer userID, Integer contactID)
            throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End," +
                " Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setTimestamp(7, createDate);
        ps.setString(8, createBy);
        ps.setTimestamp(9, lastUpdate);
        ps.setTimestamp(10, lastUpdateBy);
        ps.setInt(11, customerID);
        ps.setInt(12,userID);
        ps.setInt(13, contactID);
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
            Appointment appointment = new Appointment(id, title, description, location, type, start, end, createDate,
                    createdBy, lastUpdate, lastUpdateBy, customerID, userID, contactID);
            app.add(appointment);
        }
        return app;
    }
}
