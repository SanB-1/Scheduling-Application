package Controllers;

import Database.*;
import Model.Appointment;
import Model.Contact;
import Model.Country;
import Model.Customer;
import Utils.Helpers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Reports {

    public RadioButton radioByMonth;
    public ComboBox<String> customerComboBox;
    public RadioButton radioByType;
    public TextField totalApps;
    public ComboBox<String > byTypeBox;
    public ComboBox<String> byMonthBox;
    public ObservableList<Customer> cuList = CustomerDAO.select();
    public ObservableList<Appointment> appList = AppointmentDAO.select();
    public ObservableList<Contact> coList = ContactDAO.select();
    public ObservableList<Country> countryList = CountryDAO.countryList();


    public Reports() throws SQLException {
    }

    public void initialize(){
        for (Customer c : cuList) {
            customerComboBox.getItems().add(c.getName());
        }
        for (Appointment a : appList) {
            byTypeBox.getItems().add(a.getType());
        }

        byMonthBox.getItems().add("January");
        byMonthBox.getItems().add("February");
        byMonthBox.getItems().add("March");
        byMonthBox.getItems().add("April");
        byMonthBox.getItems().add("May");
        byMonthBox.getItems().add("June");
        byMonthBox.getItems().add("July");
        byMonthBox.getItems().add("August");
        byMonthBox.getItems().add("September");
        byMonthBox.getItems().add("October");
        byMonthBox.getItems().add("November");
        byMonthBox.getItems().add("December");

        ToggleGroup selectionToggleGroup = new ToggleGroup();
        this.radioByType.setToggleGroup(selectionToggleGroup);
        this.radioByMonth.setToggleGroup(selectionToggleGroup);

        for (Contact c : coList){
            contactBox.getItems().add(c.getName());
        }

        for (Country c : countryList){
            countryComboBox.getItems().add(c.getName());
        }

    }

    public void onCustomerComboBox(ActionEvent actionEvent) {
        totalApps.setText("");
        byTypeBox.getSelectionModel().clearSelection();
        byMonthBox.getSelectionModel().clearSelection();
        if (radioByMonth.isDisabled() && radioByType.isDisabled()){
            radioByType.setDisable(false);
            byTypeBox.setDisable(false);
            radioByMonth.setDisable(false);
            radioByType.setSelected(true);
        }
    }

    public void onRadioByMonth(ActionEvent actionEvent) {
        totalApps.setText("");
        byTypeBox.setDisable(true);
        byTypeBox.getSelectionModel().clearSelection();
        byMonthBox.setDisable(false);
    }

    public void onRadioByType(ActionEvent actionEvent) {
        totalApps.setText("");
        byTypeBox.setDisable(false);
        byMonthBox.getSelectionModel().clearSelection();
        byMonthBox.setDisable(true);
    }

    public void onByTypeBox(ActionEvent actionEvent) throws SQLException {
        if (!byTypeBox.getSelectionModel().isEmpty()) {
            ObservableList<Appointment> byType = AppointmentDAO.byType(byTypeBox.getSelectionModel().getSelectedItem(),
                    CustomerDAO.nameToID(customerComboBox.getValue()));
            int i = 0;
            if (!byType.isEmpty()) {
                for (Appointment a : byType) {
                    i++;
                }
            }
            totalApps.setText(Integer.toString(i));
        }
    }

    public void onByMonthBox(ActionEvent actionEvent) throws SQLException {
        if (!byMonthBox.getSelectionModel().isEmpty()) {
            ObservableList<Appointment> byMonth = AppointmentDAO.byMonth(
                    Helpers.months().get(byMonthBox.getSelectionModel().getSelectedItem()),
                    CustomerDAO.nameToID(customerComboBox.getValue()));
            int i = 0;
            if (!byMonth.isEmpty()) {
                for (Appointment a : byMonth) {
                    i++;
                }
            }
            totalApps.setText(Integer.toString(i));
        }
    }

    public TableView<Appointment> scheduleTableView;
    public TableColumn<Appointment, Integer> appID;
    public TableColumn<Appointment, String> title;
    public TableColumn<Appointment, String> type;
    public TableColumn<Appointment, String> description;
    public TableColumn<Appointment, Timestamp> start;
    public TableColumn<Appointment, Timestamp> end;
    public TableColumn<Appointment, Integer> customerID;

    public ComboBox<String> contactBox;

    public void onContactBox(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment> appointmentsByContact = FXCollections.observableArrayList();
        for (Appointment a : appList){
            if (a.getContactID().equals(ContactDAO.nameToID(contactBox.getSelectionModel().getSelectedItem()))){
                appointmentsByContact.add(a);
            }
        }
        scheduleTableView.setItems(appointmentsByContact);
        appID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }

    public ComboBox<String> countryComboBox;

    public TableView<Customer> customerTable;
    public TableColumn<Customer, Integer> customerID1;
    public TableColumn<Customer, String> customerName;
    public TableColumn<Customer, String> customerAddress;
    public TableColumn<Customer, String> customerZIP;
    public TableColumn<Customer, String> customerPhone;
    public TableColumn<Customer, Integer> divID;

    public void onCountryComboBox(ActionEvent actionEvent) throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        for (Customer c : cuList){
            if (FirstDivisionDAO.divIDtoCountryID(c.getDivID()) ==
                    CountryDAO.countryToID(countryComboBox.getSelectionModel().getSelectedItem())){
                customers.add(c);
            }
        }
        customerTable.setItems(customers);
        customerID1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerZIP.setCellValueFactory(new PropertyValueFactory<>("zip"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divID.setCellValueFactory(new PropertyValueFactory<>("divID"));
    }

    public void onReturn(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
    }
}
