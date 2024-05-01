package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PaymentFormController {

    @FXML
    private JFXComboBox<?> cmbJobId;

    @FXML
    private TableColumn<?, ?> colDefectTotalCost;

    @FXML
    private TableColumn<?, ?> colEmployeeTotalCost;

    @FXML
    private TableColumn<?, ?> colJobId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colSpareTotalCost;

    @FXML
    private Label lblPaymentId;

    @FXML
    private TableColumn<?, ?> lblTotalPayment;

    @FXML
    private AnchorPane paymentRoot;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void cmbJobIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(MouseEvent event) {

    }

}
