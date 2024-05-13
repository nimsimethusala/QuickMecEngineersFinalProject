package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.controller.util.GenerateQRCode;
import lk.Ijse.model.tm.PaymentTm;
import lk.Ijse.repository.CustomerRepo;
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
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtSearch;

    public void initialize(){
        getCurrentCustomerId();
        getJobId();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String jobId = (String) cmbJobId.getValue();
        String paymentId = lblPaymentId.getText();

        try {
            double defectTotalCost = PaymentRepo.getTotalDefectCost(jobId);
            double employeeTotalCost = PaymentRepo.getTotalEmployeeCost(jobId);
            double spareTotalCost = PaymentRepo.getTotalSpareCost(jobId);

            double total = defectTotalCost + employeeTotalCost + spareTotalCost;

            ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

            PaymentTm paymentTm = new PaymentTm(paymentId, jobId, defectTotalCost, employeeTotalCost, spareTotalCost, total);
            obList.add(paymentTm);

            tblPayment.setItems(obList);

            boolean isPaymentPlaced = PaymentRepo.save(paymentTm);

            if (isPaymentPlaced){
                new Alert(Alert.AlertType.CONFIRMATION, "New Payment is placed successfully...!");

            }else {
                new Alert(Alert.AlertType.ERROR, "New Payment is not placed unsuccessfully...!");
            }

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

    private void getCurrentCustomerId() {
        try {
            String nextPaymentId = PaymentRepo.generateNextPaymentId();
            lblPaymentId.setText(nextPaymentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}