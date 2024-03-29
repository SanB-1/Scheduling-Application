package Controllers;

import Database.AppointmentDAO;
import Database.ContactDAO;
import Database.CustomerDAO;
import Database.UsersDAO;
import Model.Contact;
import Utils.Helpers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * AddAppointment Controller class.
 */
public class AddAppointment {

    public TextField addAppIDField;
    public ComboBox<String> addAppUserBox;
    public ComboBox<String> addAppCustomerBox;
    public TextField addAppTitleField;
    public TextField addAppDescField;
    public TextField addAppLocationField;
    public ComboBox<String> addAppContactBox;
    public TextField addAppTypeField;
    public DatePicker addAppSelStartDate;
    public DatePicker addAppSelEndDate;
    public ComboBox<String> addAppStartTimeBox;
    public ComboBox<String> addAppEndTimeBox;
    public ArrayList<String> uList = UsersDAO.userIDList();
    public ArrayList<String> cList = CustomerDAO.customerIDList();
    public ObservableList<Contact> coList = ContactDAO.select();

    /**
     * Method to throw the SQLException.
     * @throws SQLException
     */
    public AddAppointment() throws SQLException {
    }

    public Button cancelButton;

    /**
     * Initializer method:
     * Sets the "cancel" button to go back to the Main view by using a lambda expression.
     * Populates the combo boxes.
     */
    public void initialize() {

        cancelButton.setOnAction(e -> {
            try {
                Helpers.nextScene(e, "/Views/Main.fxml", "Reports");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        for (String id : cList) {
            addAppCustomerBox.getItems().add(id);
        }

        for (String id : uList) {
            addAppUserBox.getItems().add(id);
        }

        for (Contact c : coList) {
            addAppContactBox.getItems().add(c.getName());
        }
        addAppStartTimeBox.setItems(Helpers.times());
        addAppEndTimeBox.setItems(Helpers.times());
    }

    /**
     * Event for the "Add Appointment" Button:
     * Sets the "start" and "end" variables to timestamps with the selected values.
     * Checks for invalid inputs.
     * If all inputs are valid a new Appointment object is created and inserted into the database by the
     * Appointment DAO.
     * The user is then returned to the Main view.
     * @param actionEvent
     * @throws SQLException
     */
    public void onAddAppAddB(ActionEvent actionEvent) throws SQLException {

        Timestamp start = Timestamp.valueOf(
                addAppSelStartDate.getValue().toString() + " " + addAppStartTimeBox.getValue() + ":00.000");
        Timestamp end = Timestamp.valueOf(
                addAppSelEndDate.getValue().toString() + " " + addAppEndTimeBox.getValue() + ":00.000");

        if (start.after(end)){
            Helpers.displayMessage("Starting time has to be before Ending time.");
        }
        else if (addAppUserBox.getSelectionModel().getSelectedItem() == null ||
                addAppCustomerBox.getSelectionModel().getSelectedItem() == null || addAppTitleField.getText().equals("")
                || addAppDescField.getText().equals("") || addAppLocationField.getText().equals("") ||
                addAppContactBox.getSelectionModel().getSelectedItem() == null || addAppTypeField.getText().equals("")
                || addAppSelStartDate.getValue() == null || addAppSelEndDate.getValue() == null ||
                addAppStartTimeBox.getSelectionModel().getSelectedItem() == null ||
                addAppEndTimeBox.getSelectionModel().getSelectedItem() == null){
            Helpers.displayMessage("Please complete all the fields.");
        }
        else if (Helpers.systemToEST(start).getHours() < 8 || Helpers.systemToEST(end).getHours() > 22){
            Helpers.displayMessage("Appointment not within business hours ( 8AM to 10PM EST).");
        }
        else if (AppointmentDAO.overlap(start, end, -100,
                Integer.parseInt(addAppCustomerBox.getSelectionModel().getSelectedItem()))){
            Helpers.displayMessage("This appointment is overlapping with a another appointment for the same customer.");
        }
        else{
            try {
                AppointmentDAO.insert(addAppTitleField.getText(), addAppDescField.getText(),
                        addAppLocationField.getText(),addAppTypeField.getText(), start, end,
                        Helpers.systemToUTC(new Timestamp(System.currentTimeMillis())), Login.currentUser,
                        Helpers.systemToUTC(new Timestamp(System.currentTimeMillis())), Login.currentUser,
                        addAppCustomerBox.getValue(), addAppUserBox.getValue(),
                        Integer.toString(ContactDAO.nameToID(addAppContactBox.getValue())));
                Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
            } catch (Exception e) {
                Helpers.displayError(e, "Error: ");
            }
        }
    }
}
