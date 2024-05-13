package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.util.Regex;
import lk.Ijse.util.TextFeildRegex;
import lk.Ijse.model.Defect;
import lk.Ijse.model.tm.DefectTm;
import lk.Ijse.repository.DefectRepo;
import lk.Ijse.repository.SpareRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DefectFormController {
    @FXML
    public JFXComboBox cmbSpareId;

    public Label lblDefectId;

    @FXML
    private AnchorPane DefectRoot;

    @FXML
    private TableView<DefectTm> tblDefect;

    @FXML
    private TableColumn<?, ?> colDefectId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearch;
    private int idCounter;

    public void initialize() {
        txtDescription.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPrice.requestFocus();
            }
        });

        getCurrentDefectId();
        getSpareId();
        setCellValueFactory();
        loadAllDefect();
    }

    private void getCurrentDefectId() {
        try {
            //String orderId = CustomerRepo.GetOrderId();

            String nextOrderId = DefectRepo.generateNextDefectId();
            lblDefectId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*private void generateCustomerId() {
        String customerId = String.format("D%03d", idCounter);
        lblDefectId.setText(customerId);
    }

    private void incrementIdCounter() {
        idCounter++;
        generateCustomerId();
    }*/

    private void loadAllDefect() {
        ObservableList<DefectTm> odList = FXCollections.observableArrayList();

        try {
            List<Defect> defectList = DefectRepo.getAll();
            for (Defect defect : defectList){
                DefectTm defectTm = new DefectTm(defect.getDefectId(), defect.getDescription(), defect.getPrice());
                odList.add(defectTm);
            }

            tblDefect.setItems(odList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colDefectId.setCellValueFactory(new PropertyValueFactory<>("defectId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String defectId = lblDefectId.getText();

        try {
            boolean isDeleted = DefectRepo.delete(defectId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Defect is deleted...!").show();
                clearFeilds();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String defectId = lblDefectId.getText();
        String desc = txtDescription.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String spareId = (String) cmbSpareId.getValue();

        Defect defect = new Defect(defectId, desc, price, spareId);

        try {
            boolean isSaved = DefectRepo.save(defect);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"New Defect is Saved...!").show();
                clearFeilds();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String defectId = lblDefectId.getText();
        String desc = txtDescription.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String spareId = (String) cmbSpareId.getValue();

        Defect defect = new Defect(defectId, desc, price, spareId);
        try {
            boolean isUpdate = DefectRepo.update(defect);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Defect is updated...!");
                clearFeilds();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void imgBackOnAction(MouseEvent mouseEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) DefectRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getSpareId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = SpareRepo.getId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbSpareId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFeilds(){
        txtDescription.setText("");
        txtPrice.setText("");
    }

    public void txtPriceOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.PRICE,txtPrice);
    }
}
