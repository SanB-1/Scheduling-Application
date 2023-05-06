package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class Main {

    public Tab mainMenuTab;
    public Tab addCTab;
    public Tab modCTab;
    public Tab addAppTab;
    public Tab modAppTab;

    public TableView customerTable;
    public TableColumn customerID;
    public TableColumn customerName;
    public TableColumn customerAddress;
    public TableColumn customerZIP;
    public TableColumn customerPhone;

    public void onAddCustomer(ActionEvent actionEvent) {
    }

    public void onModifyCustomer(ActionEvent actionEvent) {
    }

    public void onDeleteCustomer(ActionEvent actionEvent) {
    }

    public void onAddAppointment(ActionEvent actionEvent) {
    }

    public TableView appointments;
    public TableColumn appointmentID;
    public TableColumn appointmentTitle;
    public TableColumn appointmentDescription;
    public TableColumn appointmentLocation;
    public TableColumn appointmentContact;
    public TableColumn appointmentTime;
    public TableColumn appointmentStartTime;
    public TableColumn appointmentEndTime;
    public TableColumn appointmentCustomerID;
    public TableColumn appointmentUserID;

    public void onModifyAppointment(ActionEvent actionEvent) {
    }
    public void onDeleteAppointment(ActionEvent actionEvent) {
    }

    public TableView monthAppointments;
    public TableColumn monthID;
    public TableColumn monthTitle;
    public TableColumn monthDescription;
    public TableColumn monthLocation;
    public TableColumn monthContact;
    public TableColumn monthType;
    public TableColumn monthStart;
    public TableColumn monthEnd;
    public TableColumn monthCustID;
    public TableColumn monthUID;

    public void onModifyMAppointment(ActionEvent actionEvent) {
    }

    public void onDeleteMAppointment(ActionEvent actionEvent) {
    }

    public TableView weekAppointments;
    public TableColumn weekID;
    public TableColumn weekTitle;
    public TableColumn weekDescription;
    public TableColumn weekLocation;
    public TableColumn weekContact;
    public TableColumn weekType;
    public TableColumn weekStart;
    public TableColumn weekEnd;
    public TableColumn weekCustID;
    public TableColumn weekUID;

    public void onModifyWAppointment(ActionEvent actionEvent) {
    }

    public void onDeleteWAppointment(ActionEvent actionEvent) {
    }

    public TextField addCustNameField;
    public TextField addCustAddressField;
    public ComboBox addCustCountryBox;
    public ComboBox addCustStateBox;
    public TextField addCustZipField;
    public TextField addCustPhoneField;

    public void onAddCustAddB(ActionEvent actionEvent) {
    }

    public void addAddCustCancelB(ActionEvent actionEvent) {
    }

    public TextField modCustNameField;
    public TextField modCustAddressField;
    public ComboBox modCustCountryBox;
    public ComboBox modCustStateBox;
    public TextField modCustZipField;
    public TextField modCustPhoneField;

    public void onModCustModB(ActionEvent actionEvent) {
    }

    public void onModCustCancelB(ActionEvent actionEvent) {
    }

    public TextField addAppIDField;
    public TextField addAppUIDField;
    public TextField addAppCustIDFIeld;
    public TextField addAppTitleField;
    public TextField addAppDescField;
    public ComboBox addAppLocBox;
    public ComboBox addAppContactBox;
    public ComboBox addAppTypeBox;
    public DatePicker addAppSelStartDate;
    public DatePicker addAppSelEndDate;
    public ChoiceBox addAppStartTimeBox;
    public ChoiceBox addAppEndTimeBox;

    public void onAddAppAddB(ActionEvent actionEvent) {
    }

    public void onAddAppCancelB(ActionEvent actionEvent) {
    }

    public TextField modAppIDFIeld;
    public TextField modAppUIDField;
    public TextField modAppCustIDField;
    public TextField modAppTitleField;
    public TextField modAppDescriptionField;
    public ComboBox modAppLocationBox;
    public ComboBox modAppContactBox;
    public ComboBox modAppTypeBox;
    public DatePicker modAppStartDate;
    public DatePicker modAppEndDate;
    public ChoiceBox modAppStartBox;
    public ChoiceBox modAppEndBox;

    public void onModAppModB(ActionEvent actionEvent) {
    }

    public void onModAppCancelB(ActionEvent actionEvent) {
    }

}
