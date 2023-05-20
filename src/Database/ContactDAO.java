package Database;

import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for the Contact DAO
 */
public class ContactDAO {

    /**
     * Returns an Observable List containing all the Contacts in the contact table.
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns the Contact ID corresponding to the selected Contact.
     * @param cName
     * @return
     * @throws SQLException
     */
    public static Integer nameToID(String cName) throws SQLException {
        String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, cName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("Contact_ID");
    }

    /**
     * Returns the Contact Name corresponding to the selected Contact ID.
     * @param id
     * @return
     * @throws SQLException
     */
    public static String IDtoName(int id) throws SQLException {
        String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID = ?;";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("Contact_Name");
    }
}
