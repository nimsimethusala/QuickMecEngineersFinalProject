package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SpareFormController {

    @FXML
    private AnchorPane SpareRoot;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TableView<?> tblSpare;

    @FXML
    private TableColumn<?, ?> tblSpareId;

    @FXML
    private TableColumn<?, ?> tblType;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSapreId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtType;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtSpareIdOnAction(ActionEvent event) {

    }
    @FXML
    void imgBackOnAction(MouseEvent mouseEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) SpareRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtSapreId.setText("");
        txtName.setText("");
        txtType.setText("");
    }
}
