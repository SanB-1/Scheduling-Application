package Controllers;

import Database.CountryDAO;
import Database.CustomerDAO;
import Database.FirstDivisionDAO;
import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
import Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ModifyCustomer {

    public TextField modCustIDField;
    public TextField modCustNameField;
    public TextField modCustAddressField;
    public ComboBox<String> modCustCountryBox;
    public ComboBox<String> modCustStateBox;
    public TextField modCustZipField;
    public TextField modCustPhoneField;
    public ArrayList<Country> cList = CountryDAO.countryList();
    public Timestamp createdOn;
    public String createdBy;

    public ModifyCustomer() throws SQLException {

    }

    public void initialize() {
        for (Country c : cList){
            modCustCountryBox.getItems().add(c.getName());
        }
    }

    public void popFields(Customer customer) throws SQLException {
        modCustIDField.setText(customer.getID().toString());
        modCustNameField.setText(customer.getName());
        modCustAddressField.setText(customer.getAddress());
        modCustCountryBox.getSelectionModel().select(
                CountryDAO.IDtoCountry(FirstDivisionDAO.divIDtoCountryID(customer.getDivID())));
        for (FirstLevelDivision div : FirstDivisionDAO.divsWhere(CountryDAO.countryToID(modCustCountryBox.getValue()))){
            modCustStateBox.getItems().add(div.getDivision());
        }
        modCustStateBox.getSelectionModel().select(FirstDivisionDAO.getDivisionyByID(customer.getDivID().toString()));
        modCustZipField.setText(customer.getZip());
        modCustPhoneField.setText(customer.getPhone());
        createdOn = customer.getCreateDate();
        createdBy = customer.getCreatedBy();

    }

    public void onModCustModB(ActionEvent actionEvent) {
        if (modCustStateBox.getSelectionModel().getSelectedItem() == null){
            Helpers.displayMessage("Please select a Country/State.");
        }
        else if (modCustNameField.getText().equals("") || modCustAddressField.getText().equals("") ||
                modCustZipField.getText().equals("") || modCustPhoneField.getText().equals("")){
            Helpers.displayMessage("Please fill all the fields.");
        }
        else if (modCustPhoneField.getText().length() < 10){
            Helpers.displayMessage("Please insert a valid phone number.");
        }
        else {
            try {
                CustomerDAO.update(Integer.parseInt(modCustIDField.getText()),
                        FirstDivisionDAO.divToID(modCustStateBox.getSelectionModel().getSelectedItem()),
                        modCustNameField.getText(), modCustAddressField.getText(), modCustZipField.getText(),
                        modCustPhoneField.getText(), createdOn, createdBy, new Timestamp(System.currentTimeMillis()),
                        Login.currentUser);
                Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
            } catch (Exception e) {
                Helpers.displayError(e, "Error: ");
            }
        }
    }

    public void onModCustCancelB(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
    }

    public void onMCountryComboBox(ActionEvent actionEvent) throws SQLException {
        modCustStateBox.getItems().clear();
        for (FirstLevelDivision div : FirstDivisionDAO.divsWhere(CountryDAO.countryToID(modCustCountryBox.getValue()))){
            modCustStateBox.getItems().add(div.getDivision());
        }
    }

    public void onMStateComboBox(ActionEvent actionEvent) {
    }
}
