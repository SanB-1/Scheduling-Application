package Controllers;

import Database.CountryDAO;
import Database.CustomerDAO;
import Database.FirstDivisionDAO;
import Model.Country;
import Model.FirstLevelDivision;
import Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AddCustomer {

    public TextField addCustNameField;
    public TextField addCustAddressField;
    public ComboBox<String> addCustCountryBox;
    public ComboBox<String> addCustStateBox;
    public TextField addCustZipField;
    public TextField addCustPhoneField;
    public ArrayList<Country> cList = CountryDAO.countryList();

    public AddCustomer() throws SQLException {
    }


    public void initialize() {
        for (Country c : cList){
            addCustCountryBox.getItems().add(c.getName());
        }
    }

    public void onAddCustAddB(ActionEvent actionEvent) throws SQLException, IOException {
        if (addCustStateBox.getSelectionModel().getSelectedItem() == null ||
                addCustCountryBox.getSelectionModel().getSelectedItem() == null){
            Helpers.displayMessage("Please select a Country/State.");
        }
        else if (addCustNameField.getText().equals("") || addCustAddressField.getText().equals("") ||
                addCustZipField.getText().equals("") || addCustPhoneField.getText().equals("")){
            Helpers.displayMessage("Please complete all the fields.");
        }
        else if (addCustPhoneField.getText().length() < 10){
            Helpers.displayMessage("Please insert a valid phone number.");
        }
        else {
            try {
                CustomerDAO.insert(addCustNameField.getText(), addCustAddressField.getText(), addCustZipField.getText(),
                        addCustPhoneField.getText(), new Timestamp(System.currentTimeMillis()), Login.currentUser,
                        new Timestamp(System.currentTimeMillis()), Login.currentUser,
                        FirstDivisionDAO.divToID(addCustStateBox.getSelectionModel().getSelectedItem()));
                Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
            } catch (Exception e) {
                Helpers.displayError(e, "Error: " + e);
            }
        }
    }

    public void addAddCustCancelB(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
    }

    public void onCCombobox(ActionEvent actionEvent) throws SQLException {
        addCustStateBox.getItems().clear();
        addCustStateBox.setDisable(false);
        for (FirstLevelDivision div : FirstDivisionDAO.divsWhere(CountryDAO.countryToID(addCustCountryBox.getValue()))){
            addCustStateBox.getItems().add(div.getDivision());
        }
    }

    public void onSComboBox(ActionEvent actionEvent) throws SQLException {
    }




}
