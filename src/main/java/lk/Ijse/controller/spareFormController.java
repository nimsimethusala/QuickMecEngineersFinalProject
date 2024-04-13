package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class spareFormController {

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

}
