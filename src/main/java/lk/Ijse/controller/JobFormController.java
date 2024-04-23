package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class JobFormController {

    @FXML
    private AnchorPane JobRoot;

    @FXML
    private Label lblCount;

    @FXML
    private JFXComboBox<?> lblCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox<?> lblDefectId;

    @FXML
    private JFXComboBox<?> lblItemId;

    @FXML
    private Label lblJobDate;

    @FXML
    private JFXComboBox<?> lblJobId;

    @FXML
    private Label lblName;

    @FXML
    private JFXComboBox<?> lblSpareId;

    @FXML
    private Label lblVehicleModel;

    @FXML
    private TableColumn<?, ?> tblCustomerName;

    @FXML
    private TableColumn<?, ?> tblDate;

    @FXML
    private TableColumn<?, ?> tblDefectId;

    @FXML
    private TableColumn<?, ?> tblItemId;

    @FXML
    private TableView<?> tblJob;

    @FXML
    private TableColumn<?, ?> tblJobId;

    @FXML
    private TableColumn<?, ?> tblSpareId;

    @FXML
    private TableColumn<?, ?> tblVehicleModel;

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewDefectOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewJobOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewSpareOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
