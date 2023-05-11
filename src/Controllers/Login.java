package Controllers;

import Database.JDBC;
import Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public TextField usernameField;
    public PasswordField passwordField;
    public Label locationLabel;

    public void onLogin(ActionEvent actionEvent) throws SQLException, IOException {
        if (validate(usernameField.getText(), passwordField.getText())){
            Helpers.nextScene(actionEvent, "/Resources/Main.fxml", "Main Menu");
        } else {
            Helpers.displayMessage("Invalid Credentials.");
        }
    }

    private boolean validate(String username, String password) throws SQLException {
        String sql = "SELECT Password FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String pw = rs.getString("Password");
            if (pw.equals(password)){
                return true;
            }
        }
        return false;
    }
}