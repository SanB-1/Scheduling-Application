package Controllers;

import Database.AppointmentDAO;
import Database.JDBC;
import Model.Appointment;
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
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
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
    public static int currentUserID;
    public static int currentYear = Year.now().getValue();
    public static int currentWeek = LocalDateTime.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

    /**
     * Initializer method:
     * Handles localization and translation.
     * Translates the login screen if the user's locale is French.
     * Sets the location label to the user's current Zone ID.
     */
    public void initialize (){
        ResourceBundle rb = ResourceBundle.getBundle("Utils/nat", Locale.getDefault());
        error = rb.getString("error");
        textTitle.setText(rb.getString("title"));
        textUsername.setText(rb.getString("Username"));
        textPassword.setText(rb.getString("Password"));
        textLogIn.setText(rb.getString("LogIn"));
        locationLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }

    /**
     * Validates the users credentials and logs the login attempt.
     * If the credentials are valid, the database is searched for any appointments involving the current user within
     * the next 15 minutes.
     * The user is then taken to the Main view.
     * If the credentials are invalid the user will see an error message and be able to try to log in again.
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void onLogin(ActionEvent actionEvent) throws SQLException, IOException {
        if (validate(usernameField.getText(), passwordField.getText())){
            Helpers.log(usernameField.getText(), "Successful");
            ArrayList<Appointment> apps = AppointmentDAO.within15();
            if (!apps.isEmpty()){
                Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
                for (Appointment app : apps){
                    Helpers.displayMessage("REMINDER. Appointment number " + app.getID().toString() +
                            " is on the following date and time: "+ app.getStart().toString());
                }

            }
            else {
                Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
                Helpers.displayMessage("You have no upcoming appointments within the next 15 minutes.");
            }
        } else {
            Helpers.log(usernameField.getText(), "Failed");
            Helpers.displayMessage(error);
        }
    }

    /**
     * Function to validate user credentials via SQL query.
     * Returns "true" if the username and password combination is found.
     * Returns "false" if it's not.
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    private boolean validate(String username, String password) throws SQLException {
        String sql = "SELECT Password, User_ID FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String pw = rs.getString("Password");
            int id = rs.getInt("User_ID");
            if (pw.equals(password)){
                currentUser = username;
                currentUserID = id;
                return true;
            }
        }
        return false;
    }
}