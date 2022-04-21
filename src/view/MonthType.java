package view;

import DBAccess.DBAppointments;
import controller.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MonthType implements Initializable {
    @FXML
    private TableView MonthTable;
    @FXML
    private TableColumn Month;
    @FXML
    private TableColumn mTotal;
    @FXML
    private TableView TypeTable;
    @FXML
    private TableColumn Type;
    @FXML
    private TableColumn tTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            MonthTable.setItems(DBAppointments.getMonth());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        mTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));


        try {
            TypeTable.setItems(DBAppointments.getType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }

    /**This method sets a confirmation alert that can be customized in subsequent methods.
     * @param title The title of the alert.
     * @param header the header message of the alert.
     * @param content the main message of the alert.
     * @return returns true if user clicks "OK" and false if user clicks "Cancel". */
    static boolean confirm(String title, String header, String content){
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle(title);
        alert2.setHeaderText(header);
        alert2.setContentText(content);

        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK)
            return true;
        else return false;
    }

    /**This method redirects to the previous page after a confirmation dialog returns true upon "OK" click */
    public void cancelClick(ActionEvent actionEvent) throws IOException {
        if(confirm("Warning", "Leaving page", "Would you like to leave this page?")){
            Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();
        }
    }
}
