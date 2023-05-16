package Controllers;

import Database.CountryDAO;
import Database.CustomerDAO;
import Database.FirstDivisionDAO;
import Database.JDBC;
import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
import Utils.Helpers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


    public void initialize() throws SQLException {
        for (Country c : cList){
            addCustCountryBox.getItems().add(c.getName());
        }
    }


    public void onAddCustAddB(ActionEvent actionEvent) throws SQLException, IOException {
        try {
            CustomerDAO.insert(addCustNameField.getText(), addCustAddressField.getText(), addCustZipField.getText(),
                    addCustPhoneField.getText(), new Timestamp(System.currentTimeMillis()), Login.currentUser,
                    new Timestamp(System.currentTimeMillis()), Login.currentUser, divToID(addCustStateBox.getValue()));
            Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
        } catch (Exception e){
            Helpers.displayError(e, "Error: " + e);
        }
    }

    public void addAddCustCancelB(ActionEvent actionEvent) throws IOException {
        Helpers.nextScene(actionEvent, "/Views/Main.fxml", "Main Menu");
    }

    public void onCCombobox(ActionEvent actionEvent) throws SQLException {
        addCustStateBox.getItems().clear();
        addCustStateBox.setDisable(false);
        for (FirstLevelDivision div : FirstDivisionDAO.divsWhere(countryToID(addCustCountryBox.getValue()))){
            addCustStateBox.getItems().add(div.getDivision());
        }
    }

    public void onSComboBox(ActionEvent actionEvent) throws SQLException {

    }

    private static int countryToID(String country) throws SQLException {
        ObservableList<FirstLevelDivision> cList = FXCollections.observableArrayList();
        String sql = "SELECT Country_ID from countries WHERE Country = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, country);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer name = rs.getInt("Country_ID");
        return name;
    }

    private static int divToID(String div) throws SQLException {
        ObservableList<FirstLevelDivision> cList = FXCollections.observableArrayList();
        String sql = "SELECT Division_ID from first_level_divisions WHERE Division = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, div);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer name = rs.getInt("Division_ID");
        return name;
    }
}
