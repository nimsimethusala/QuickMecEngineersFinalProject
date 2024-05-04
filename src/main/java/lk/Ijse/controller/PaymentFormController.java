package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.JobRepo;
import lk.Ijse.repository.PaymentRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PaymentFormController {

    @FXML
    private JFXComboBox cmbJobId;

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

    public void initialize(){
        getJobId();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String jobId = (String) cmbJobId.getValue();
        String paymentId = lblPaymentId.getText();

        try {
            int defectTotalCost = PaymentRepo.getTotalDefectCost(jobId);
            int employeeTotalCost = PaymentRepo.getTotalEmployeeCost(jobId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getJobId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = PaymentRepo.getId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbJobId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextJobId() {
        try {
            String nextPaymentId =  PaymentRepo.getNextPaymentID();
            lblPaymentId.setText(nextPaymentId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) paymentRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbJobIdOnAction(ActionEvent event) {
        setNextJobId();
    }

    @FXML
    void txtSearchOnAction(MouseEvent event) {

    }

}
