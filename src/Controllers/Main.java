package Controllers;

import Database.AppointmentDAO;
import Database.CustomerDAO;
import Model.Appointment;
import Model.Customer;
import Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Optional;

public class Main {

    public void Main() throws SQLException {
        refreshTables();
    }
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

    public static Customer selectedCustomer;

    public void onAddCustomer(ActionEvent actionEvent) throws IOException {
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        Helpers.nextScene(actionEvent, "/Views/AddCustomer.fxml", "Customer Addition");
    }

    public void onModifyCustomer(ActionEvent actionEvent) throws IOException, SQLException {
        if (customerTable.getSelectionModel().isEmpty()) {
            Helpers.displayMessage("Select a Customer.");
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/ModifyCustomer.fxml"));
            loader.load();

            ModifyCustomer MCController = loader.getController();
            MCController.popFields(customerTable.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent root = loader.getRoot();
            Scene scene = new Scene(root, 1080, 700);
            stage.setTitle("Customer Modification");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        if (customerTable.getSelectionModel().isEmpty()){
            Helpers.displayMessage("Select a Customer.");
        }
        else if (!AppointmentDAO.appByCustID(customerTable.getSelectionModel().getSelectedItem().getID()).isEmpty()){
            Helpers.displayMessage("Please delete all the related Appointments for this Customer.");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you would like to delete this customer?");
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.isPresent() && choice.get() == ButtonType.OK) {
                Customer c = customerTable.getSelectionModel().getSelectedItem();
                Helpers.displayMessage("Customer record for " + c.getName() + " deleted.");
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
    public TableColumn<Appointment, String> appointmentType;
    public TableColumn<Appointment, Calendar> appointmentStartTime;
    public TableColumn<Appointment, Calendar> appointmentEndTime;
    public TableColumn<Appointment, Integer> appointmentCustomerID;
    public TableColumn<Appointment, Integer> appointmentUserID;
    public TableColumn<Appointment, Integer> contact;

    public void onAddAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/AddAppointment.fxml", "Appointment Addition");
    }

    public void onModifyAppointment(ActionEvent actionEvent) throws IOException, SQLException {
        if (appointments.getSelectionModel().isEmpty()) {
            Helpers.displayMessage("Select an Appointment.");
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/ModifyAppointment.fxml"));
            loader.load();

            ModifyAppointment MAController = loader.getController();
            MAController.popFields(appointments.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent root = loader.getRoot();
            Scene scene = new Scene(root, 1080, 700);
            stage.setTitle("Appointment Modification");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        if (appointments.getSelectionModel().isEmpty()) {
            Helpers.displayMessage("Select an Appointment.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you would like to delete this appointment?");
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.isPresent() && choice.get() == ButtonType.OK) {
                Appointment a = appointments.getSelectionModel().getSelectedItem();
                Helpers.displayMessage("Appointment number " + a.getID().toString() + " deleted.");
                AppointmentDAO.delete(a.getID());
                appointments.getSelectionModel().clearSelection();
            }
        }
        refreshTables();
    }

    public TableView<Appointment> appointmentsM;
    public TableColumn<Appointment, Integer> appointmentIDM;
    public TableColumn<Appointment, String> appointmentTitleM;
    public TableColumn<Appointment, String> appointmentDescriptionM;
    public TableColumn<Appointment, String> appointmentLocationM;
    public TableColumn<Appointment, String> appointmentTypeM;
    public TableColumn<Appointment, Calendar> appointmentStartTimeM;
    public TableColumn<Appointment, Calendar> appointmentEndTimeM;
    public TableColumn<Appointment, Integer> appointmentCustomerIDM;
    public TableColumn<Appointment, Integer> appointmentUserIDM;
    public TableColumn<Appointment, Integer> contactM;

    public void onModifyMAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyAppointment.fxml", "Appointment Modification");
    }

    public void onDeleteMAppointment(ActionEvent actionEvent) throws SQLException {
        if (appointmentsM.getSelectionModel().isEmpty()){
            Helpers.displayMessage("Select an Appointment.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you would like to delete this appointment?");
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.isPresent() && choice.get() == ButtonType.OK) {
                Appointment a = appointmentsM.getSelectionModel().getSelectedItem();
                AppointmentDAO.delete(a.getID());
                appointmentsM.getSelectionModel().clearSelection();
                refreshTables();
            }
        }
    }

    public TableView<Appointment> appointmentsW;
    public TableColumn<Appointment, Integer> appointmentIDW;
    public TableColumn<Appointment, String> appointmentTitleW;
    public TableColumn<Appointment, String> appointmentDescriptionW;
    public TableColumn<Appointment, String> appointmentLocationW;
    public TableColumn<Appointment, String> appointmentTypeW;
    public TableColumn<Appointment, Calendar> appointmentStartTimeW;
    public TableColumn<Appointment, Calendar> appointmentEndTimeW;
    public TableColumn<Appointment, Integer> appointmentCustomerIDW;
    public TableColumn<Appointment, Integer> appointmentUserIDW;
    public TableColumn<Appointment, Integer> contactW;

    public void onModifyWAppointment(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/ModifyAppointment.fxml", "Appointment Modification");
    }

    public void onDeleteWAppointment(ActionEvent actionEvent) throws SQLException {
        if (appointmentsM.getSelectionModel().isEmpty()){
            Helpers.displayMessage("Select an Appointment.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you would like to delete this appointment?");
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.isPresent() && choice.get() == ButtonType.OK) {
                Appointment a = appointmentsM.getSelectionModel().getSelectedItem();
                AppointmentDAO.delete(a.getID());
                appointmentsM.getSelectionModel().clearSelection();
                refreshTables();
            }
        }
    }

    private void refreshTables() throws SQLException {
        customerTable.setItems(CustomerDAO.select());
        customerID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerZIP.setCellValueFactory(new PropertyValueFactory<>("zip"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divID.setCellValueFactory(new PropertyValueFactory<>("divID"));

        appointments.setItems(AppointmentDAO.select());
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEndTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contactID"));

        appointmentsM.setItems(AppointmentDAO.select());
        appointmentIDM.setCellValueFactory(new PropertyValueFactory<>("ID"));
        appointmentTitleM.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionM.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationM.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentTypeM.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartTimeM.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEndTimeM.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerIDM.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentUserIDM.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactM.setCellValueFactory(new PropertyValueFactory<>("contactID"));

        appointmentsW.setItems(AppointmentDAO.select());
        appointmentIDW.setCellValueFactory(new PropertyValueFactory<>("ID"));
        appointmentTitleW.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionW.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationW.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentTypeW.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartTimeW.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEndTimeW.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentCustomerIDW.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentUserIDW.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactW.setCellValueFactory(new PropertyValueFactory<>("contactID"));
    }
}