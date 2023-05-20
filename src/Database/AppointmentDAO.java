package Database;

import Controllers.Login;
import Model.Appointment;
import Utils.Helpers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class for the Appointment DAO
 */
public class AppointmentDAO {

    /**
     * Inserts a new Appointment into the appointments table.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createBy
     * @param lastUpdate
     * @param lastUpdateBy
     * @param customerID
     * @param userID
     * @param contactID
     * @throws SQLException
     */
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

    /**
     * Selects all Appointments from the appointments table.
     * @return
     * @throws SQLException
     */
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

    /**
     * Updates the selected Appointment from the appointments table.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createBy
     * @param lastUpdate
     * @param lastUpdateBy
     * @param customerID
     * @param userID
     * @param contactID
     * @param ID
     * @throws SQLException
     */
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

    /**
     * Deletes the selected Appointment from the appointments table.
     * @param aID
     * @return
     * @throws SQLException
     */
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

    /**
     * Selects all Appointments for the selected Customer from the appointments table.
     * @param cID
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns "true" if there is an overlapping appointment with a different Appointment ID for the same Customer in
     * the appointments table.
     * @param start
     * @param aID
     * @param cID
     * @return
     * @throws SQLException
     */
    public static boolean overlap(Timestamp start, Timestamp end, int aID, int cID) throws SQLException {
        String sql = "SELECT Title, Appointment_ID, Customer_ID FROM appointments " +
                "WHERE (? BETWEEN Start AND End " +
                "OR ? BETWEEN Start AND End) " +
                "AND ? <> Appointment_ID " +
                "AND ? = Customer_ID;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setTimestamp(1, Helpers.systemToUTC(start));
        ps.setTimestamp(2, Helpers.systemToUTC(end));
        ps.setInt(3, aID);
        ps.setInt(4, cID);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    /**
     * Returns an arraylist containing all the Appointments happening within 15 minutes of the query in the appointments
     * table.
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns an Observable List of all the Appointments in the appointments table containing the selected type.
     * @param inType
     * @param inID
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> byType(String inType, int inID) throws SQLException {
        ObservableList<Appointment> app = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Type = ? AND Customer_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, inType);
        ps.setInt(2, inID);
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

    /**
     * Returns an Observable List of all the Appointments in the appointments table during the selected month
     * corresponding to the selected Customer.
     * @param month
     * @param inID
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> byMonth(int month, int inID) throws SQLException {
        ObservableList<Appointment> app = FXCollections.observableArrayList();
        String sql = "SELECT *, MONTH(Start) FROM appointments WHERE MONTH(Start) = ? AND Customer_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, month);
        ps.setInt(2, inID);
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

    /**
     * Returns an Observable List of all the Appointments in the appointments table during the selected month.
     * @param month
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> allByMonth(int month) throws SQLException {
        ObservableList<Appointment> app = FXCollections.observableArrayList();
        String sql = "SELECT *, MONTH(Start) FROM appointments WHERE MONTH(Start) = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, month);
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

    /**
     * Returns an Observable List of all the Appointments in the appointments during the current week.
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> allByCurrentWeek() throws SQLException {
        ObservableList<Appointment> app = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE WEEK(Start, 1) = ? AND YEAR(Start) = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, Login.currentWeek);
        ps.setInt(2, Login.currentYear);
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
}
