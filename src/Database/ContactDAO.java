package Database;

import Model.Appointment;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAO {

    public static ObservableList<Contact> select() throws SQLException {
        ObservableList<Contact> cont = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact contact = new Contact(id, name, email);
            cont.add(contact);
        }
        return cont;
    }

    public static String nameToID(String cName) throws SQLException {
        String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, cName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("Contact_ID");
    }

    public static String IDtoName(int id) throws SQLException {
        String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("Contact_Name");
    }
}
