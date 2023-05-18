package Controllers;

import Database.JDBC;
import Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login {

    public TextField usernameField;
    public PasswordField passwordField;
    public Label locationLabel;
    public Label textUsername;
    public Label textPassword;
    public Button textLogIn;
    public Label textTitle;
    public String error;
    public static String currentUser;

    public void initialize (){
        ResourceBundle rb = ResourceBundle.getBundle("Utils/nat", Locale.getDefault());
        error = rb.getString("error");
        textTitle.setText(rb.getString("title"));
        textUsername.setText(rb.getString("Username"));
        textPassword.setText(rb.getString("Password"));
        textLogIn.setText(rb.getString("LogIn"));
        locationLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }

    public void onLogin(ActionEvent actionEvent) throws SQLException, IOException {
        if (validate(usernameField.getText(), passwordField.getText())){
            Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
        } else {
            Helpers.displayMessage(error);
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
                currentUser = username;
                return true;
            }
        }
        return false;
    }
}