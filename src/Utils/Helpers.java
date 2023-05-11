package Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public abstract class Helpers {

    public static void nextScene(ActionEvent actionEvent, String location, String title) throws IOException{
        Parent root = FXMLLoader.load(Helpers.class.getResource(location));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1080, 700);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void displayError(Exception e, String message){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(message);
        errorAlert.showAndWait();
        e.printStackTrace();
    }

    public static void displayMessage(String message){
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText(message);
        errorAlert.showAndWait();
    }

}
