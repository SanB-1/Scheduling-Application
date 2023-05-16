package Controllers;

import Database.CustomerDAO;
import Model.Appointment;
import Model.Customer;
import Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

public class Main {

    public void initialize() throws SQLException{
        refreshTables();
    }

    public Tab mainMenuTab;
    public Tab addCTab;
    public Tab modCTab;
    public Tab addAppTab;
    public Tab modAppTab;

    public TableView<Customer> customerTable;
    public TableColumn<Customer, Integer> customerID;
    public TableColumn<Customer, String> customerName;
    public TableColumn<Customer, String> customerAddress;
    public TableColumn<Customer, String> customerZIP;
    public TableColumn<Customer, String> customerPhone;
    public TableColumn<Customer, Integer> divID;

    public void onAddCustomer(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/AddCustomer.fxml", "Customer Addition");
    }

    public void onModifyCustomer(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyCustomer.fxml", "Customer Modification");
    }

    public void onDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        if (customerTable.getSelectionModel().isEmpty()){
            Helpers.displayMessage("Select a Customer.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?");
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.isPresent() && choice.get() == ButtonType.OK) {
                Customer c = customerTable.getSelectionModel().getSelectedItem();
                CustomerDAO.delete(c.getID());
                customerTable.getSelectionModel().clearSelection();
            }
        }
        refreshTables();
    }

    public TableView<Appointment> appointments;
    public TableColumn<Appointment, Integer> appointmentID;
    public TableColumn<Appointment, String> appointmentTitle;
    public TableColumn<Appointment, String> appointmentDescription;
    public TableColumn<Appointment, String> appointmentLocation;
    public TableColumn<Appointment, String> appointmentContact;
    public TableColumn<Appointment, String> appointmentType;
    public TableColumn<Appointment, Calendar> appointmentStartTime;
    public TableColumn<Appointment, Calendar> appointmentEndTime;
    public TableColumn<Appointment, Integer> appointmentCustomerID;
    public TableColumn<Appointment, Integer> appointmentUserID;

    public void onAddAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/AddAppointment.fxml", "Appointment Addition");
    }

    public void onModifyAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyAppointment.fxml", "Appointment Modification");
    }

    public void onDeleteAppointment(ActionEvent actionEvent){
    }

    public TableView<Appointment> appointmentsM;
    public TableColumn<Appointment, Integer> appointmentIDM;
    public TableColumn<Appointment, String> appointmentTitleM;
    public TableColumn<Appointment, String> appointmentDescriptionM;
    public TableColumn<Appointment, String> appointmentLocationM;
    public TableColumn<Appointment, String> appointmentContactM;
    public TableColumn<Appointment, String> appointmentTypeM;
    public TableColumn<Appointment, Calendar> appointmentStartTimeM;
    public TableColumn<Appointment, Calendar> appointmentEndTimeM;
    public TableColumn<Appointment, Integer> appointmentCustomerIDM;
    public TableColumn<Appointment, Integer> appointmentUserIDM;

    public void onModifyMAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyAppointment.fxml", "Appointment Modification");
    }

    public void onDeleteMAppointment(ActionEvent actionEvent) {
    }

    public TableView<Appointment> appointmentsW;
    public TableColumn<Appointment, Integer> appointmentIDW;
    public TableColumn<Appointment, String> appointmentTitleW;
    public TableColumn<Appointment, String> appointmentDescriptionW;
    public TableColumn<Appointment, String> appointmentLocationW;
    public TableColumn<Appointment, String> appointmentContactW;
    public TableColumn<Appointment, String> appointmentTypeW;
    public TableColumn<Appointment, Calendar> appointmentStartTimeW;
    public TableColumn<Appointment, Calendar> appointmentEndTimeW;
    public TableColumn<Appointment, Integer> appointmentCustomerIDW;
    public TableColumn<Appointment, Integer> appointmentUserIDW;

    public void onModifyWAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyAppointment.fxml", "Appointment Modification");
    }

    public void onDeleteWAppointment(ActionEvent actionEvent) {
    }

    private void refreshTables() throws SQLException {
        customerTable.setItems(CustomerDAO.select());
        customerID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerZIP.setCellValueFactory(new PropertyValueFactory<>("zip"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divID.setCellValueFactory(new PropertyValueFactory<>("divID"));
    }
}
