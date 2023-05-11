package Controllers;

import Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

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

    public void onAddCustomer(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/AddCustomer.fxml", "Customer Addition");
    }

    public void onModifyCustomer(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyCustomer.fxml", "Customer Modification");
    }

    public void onDeleteCustomer(ActionEvent actionEvent) {
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

    public void onAddAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/AddAppointment.fxml", "Appointment Addition");
    }

    public void onModifyAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyAppointment.fxml", "Appointment Modification");
    }

    public void onDeleteAppointment(ActionEvent actionEvent){

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

    public void onModifyMAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyAppointment.fxml", "Appointment Modification");
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

    public void onModifyWAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyAppointment.fxml", "Appointment Modification");
    }

    public void onDeleteWAppointment(ActionEvent actionEvent) {
    }

}
