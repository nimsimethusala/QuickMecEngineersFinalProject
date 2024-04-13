package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class defectFormController {

    @FXML
    private AnchorPane DefectRoot;

    @FXML
    private TableView<?> tblDefect;

    @FXML
    private TableColumn<?, ?> tblDefectId;

    @FXML
    private TableColumn<?, ?> tblDescription;

    @FXML
    private TableColumn<?, ?> tblPrice;

    @FXML
    private TextField txtDefectId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

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
    void txtDefectIdOnAction(ActionEvent event) {

    }

}
