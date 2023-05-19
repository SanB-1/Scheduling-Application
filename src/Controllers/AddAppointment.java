package Controllers;

import Database.AppointmentDAO;
import Database.ContactDAO;
import Database.CustomerDAO;
import Database.UsersDAO;
import Model.Contact;
import Utils.Helpers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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

    public AddAppointment() throws SQLException {
    }

    public void initialize() throws SQLException {
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

    public void onAddAppAddB(ActionEvent actionEvent) {

        Timestamp start = Timestamp.valueOf(
                addAppSelStartDate.getValue().toString() + " " + addAppStartTimeBox.getValue() + ":00.000");
        Timestamp end = Timestamp.valueOf(
                addAppSelEndDate.getValue().toString() + " " + addAppEndTimeBox.getValue() + ":00.000");

        if (start.after(end)){
            Helpers.displayMessage("Starting time has to be before Ending time.");
        }

        else{
            try {
                AppointmentDAO.insert(addAppTitleField.getText(), addAppDescField.getText(),
                        addAppLocationField.getText(),addAppTypeField.getText(), start, end,
                        Helpers.systemToUTC(new Timestamp(System.currentTimeMillis())), Login.currentUser,
                        Helpers.systemToUTC(new Timestamp(System.currentTimeMillis())), Login.currentUser,
                        addAppCustomerBox.getValue(), addAppUserBox.getValue(),
                        ContactDAO.nameToID(addAppContactBox.getValue()));
                Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
            } catch (Exception e) {
                Helpers.displayError(e, "Error: ");
            }
        }
    }

    public void onAddAppCancelB (ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
    }
}
