package Controllers;

import Database.*;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Utils.Helpers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModifyAppointment {

    public TextField modAppIDFIeld;
    public ComboBox<String> modAppUIDBox;
    public ComboBox<String> modAppCustIDBox;
    public TextField modAppTitleField;
    public TextField modAppDescriptionField;
    public TextField modAppLocationField;
    public ComboBox<String> modAppContactBox;
    public TextField modAppTypeField;
    public DatePicker modAppStartDate;
    public DatePicker modAppEndDate;
    public ComboBox<String> modAppStartBox;
    public ComboBox<String> modAppEndBox;
    public ArrayList<String> uList = UsersDAO.userIDList();
    public ArrayList<String> cList = CustomerDAO.customerIDList();
    public ObservableList<Contact> coList = ContactDAO.select();

    public ModifyAppointment() throws SQLException {
    }

    public void initialize() {
        for (String id : cList) {
            modAppCustIDBox.getItems().add(id);
        }

        for (String id : uList) {
            modAppUIDBox.getItems().add(id);
        }

        for (Contact c : coList) {
            modAppContactBox.getItems().add(c.getName());
        }
        modAppStartBox.setItems(Helpers.times());
        modAppEndBox.setItems(Helpers.times());
    }

    public void popFields(Appointment appointment) throws SQLException {
        modAppIDFIeld.setText(appointment.getID().toString());
        modAppUIDBox.getSelectionModel().select(appointment.getUserID().toString());
        modAppCustIDBox.getSelectionModel().select(appointment.getCustomerID().toString());
        modAppTitleField.setText(appointment.getTitle());
        modAppDescriptionField.setText(appointment.getDescription());
        modAppLocationField.setText(appointment.getLocation());
        modAppContactBox.getSelectionModel().select(ContactDAO.IDtoName(appointment.getContactID()));
        modAppTypeField.setText(appointment.getType());
        modAppStartDate.setValue(appointment.getStart().toLocalDateTime().toLocalDate());
        modAppEndDate.setValue(appointment.getEnd().toLocalDateTime().toLocalDate());
        modAppStartBox.getSelectionModel().select(
                String.valueOf(appointment.getStart().toLocalDateTime().toLocalTime()));
        modAppEndBox.getSelectionModel().select(String.valueOf(appointment.getEnd().toLocalDateTime().toLocalTime()));

    }

    public void onModAppModB(ActionEvent actionEvent) {
    }

    public void onModAppCancelB(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
    }

}