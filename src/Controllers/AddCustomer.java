package Controllers;

import Database.CountryDAO;
import Database.CustomerDAO;
import Database.FirstDivisionDAO;
import Model.Country;
import Model.FirstLevelDivision;
import Utils.Helpers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * AddCustomer Controller class.
 */
public class AddCustomer {

    public TextField addCustNameField;
    public TextField addCustAddressField;
    public ComboBox<String> addCustCountryBox;
    public ComboBox<String> addCustStateBox;
    public TextField addCustZipField;
    public TextField addCustPhoneField;
    public ObservableList<Country> cList = CountryDAO.countryList();

    /**
     * Method to throw the SQLException.
     * @throws SQLException
     */
    public AddCustomer() throws SQLException {
    }

    /**
     * Initializer method:
     * Populates the combo box.
     */
    public void initialize() {
        for (Country c : cList){
            addCustCountryBox.getItems().add(c.getName());
        }
    }

    /**
     * Event for the "Add Customer" Button:
     * Checks for invalid inputs.
     * If all inputs are valid a new Customer object is created and inserted into the database by the Customer DAO.
     * The user is then returned to the Main view.
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
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

    /**
     * Returns the user back to the Main view after pressing the "Cancel" Button.
     * @param actionEvent
     * @throws IOException
     */
    public void addAddCustCancelB(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
    }


    /**
     * This method is activated once a country is selected on the combo box.
     * Clears the selected item in the First Division combo box if any are present.
     * Enables the user to edit the First Division combo box if it is disabled.
     * Populates the First Division combo box with locations belonging to the selected Country.
     * @param actionEvent
     * @throws SQLException
     */
    public void onCCombobox(ActionEvent actionEvent) throws SQLException {
        addCustStateBox.getItems().clear();
        addCustStateBox.setDisable(false);
        for (FirstLevelDivision div : FirstDivisionDAO.divsWhere(CountryDAO.countryToID(addCustCountryBox.getValue()))){
            addCustStateBox.getItems().add(div.getDivision());
        }
    }
}
