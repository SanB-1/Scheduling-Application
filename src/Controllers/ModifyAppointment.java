package Controllers;

import Database.*;
import Model.Appointment;
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
    public Timestamp createdOn;
    public String createdBy;

    /**
     * Class for the ModifyAppointment controller.
     * @throws SQLException
     */
    public ModifyAppointment() throws SQLException {
    }

    /**
     * Initializer Method:
     * Populates all combo boxes.
     */
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

    /**
     * Populates all the fields with the data extracted from the selected row.
     * This method is called inside the Main controller so the selected Appointment can be used as an argument.
     * @param appointment
     * @throws SQLException
     */
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
        createdOn = appointment.getCreateDate();
        createdBy = appointment.getCreatedBy();
    }

    /**
     * Event for the "Modify Appointment" Button:
     * Sets the "start" and "end" variables to timestamps with the selected values.
     * Checks for invalid inputs.
     * If all inputs are valid a new Appointment object is created and the old Appointment is updated via the
     * Appointment DAO.
     * The user is then returned to the Main view.
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void onModAppModB(ActionEvent actionEvent) throws SQLException, IOException {
        Timestamp start = Timestamp.valueOf(
                modAppStartDate.getValue().toString() + " " + modAppStartBox.getValue() + ":00.000");
        Timestamp end = Timestamp.valueOf(
                modAppEndDate.getValue().toString() + " " + modAppEndBox.getValue() + ":00.000");
        if (modAppUIDBox.getSelectionModel().getSelectedItem() == null ||
                modAppCustIDBox.getSelectionModel().getSelectedItem() == null || modAppTitleField.getText().equals("")
                || modAppDescriptionField.getText().equals("") || modAppLocationField.getText().equals("") ||
                modAppContactBox.getSelectionModel().getSelectedItem() == null || modAppTypeField.getText().equals("")
                || modAppStartDate.getValue() == null || modAppEndDate.getValue() == null ||
                modAppStartBox.getSelectionModel().getSelectedItem() == null ||
                modAppEndBox.getSelectionModel().getSelectedItem() == null){
            Helpers.displayMessage("Please complete all the fields.");
        }
        else if (modAppStartDate.getValue().isAfter(modAppEndDate.getValue())){
            Helpers.displayMessage("Invalid date/time combinations.");
        }
        else if (Helpers.systemToEST(start).getHours() < 8 || Helpers.systemToEST(end).getHours() > 22){
            Helpers.displayMessage("Appointment not within business hours ( 8AM to 10PM EST).");
        }
        else if (AppointmentDAO.overlap(start, end, Integer.parseInt(modAppIDFIeld.getText()),
                Integer.parseInt(modAppCustIDBox.getSelectionModel().getSelectedItem()))){
            Helpers.displayMessage("This appointment is overlapping with a another appointment for the same customer.");
        }
        else {
            AppointmentDAO.update(modAppTitleField.getText(), modAppDescriptionField.getText(),
                    modAppLocationField.getText(), modAppTypeField.getText(),
                    start, end, createdOn, createdBy, new Timestamp(System.currentTimeMillis()), Login.currentUser,
                    modAppCustIDBox.getSelectionModel().getSelectedItem(),
                    modAppUIDBox.getSelectionModel().getSelectedItem(),
                    Integer.toString(ContactDAO.nameToID(modAppContactBox.getSelectionModel().getSelectedItem())),
                    modAppIDFIeld.getText());
            Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
        }
    }

    /**
     * Returns the user to the Main view upon clicking the "Cancel" button.
     * @param actionEvent
     * @throws IOException
     */
    public void onModAppCancelB(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
    }

}