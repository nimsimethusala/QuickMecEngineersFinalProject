package lk.Ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.model.Customer;
import lk.Ijse.model.Supplier;
import lk.Ijse.model.tm.SupplierTm;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.SupplierRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {
    public Label lblSupplierId;

    @FXML
    private AnchorPane supplierRoot;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSupplierName;

    private int idCounter;

    public void initialize() {
        txtSupplierName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtContactNo.requestFocus();
            }
        });

        txtContactNo.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLocation.requestFocus();
            }
        });

        getCurrentSpareId();
        setCellValueFactory();
        loadAllSupplier();
    }

    private void getCurrentSpareId() {
        try {
            String nextOrderId = SupplierRepo.generateNextSupplierId();
            lblSupplierId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = SupplierRepo.getAll();
            for (Supplier supplier : supplierList){
                SupplierTm supplierTm = new SupplierTm(supplier.getSupplierId(), supplier.getName(), supplier.getContact(), supplier.getLocation());
                obList.add(supplierTm);
            }
            tblSupplier.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String supId = lblSupplierId.getText();

        try {
            boolean isDeleted = SupplierRepo.delete(supId);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier is deleted successfully...!").show();
                clearFeilds();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String supId = lblSupplierId.getText();
        String supName = txtSupplierName.getText();
        int contact = Integer.parseInt(txtContactNo.getText());
        String location = txtLocation.getText();

        Supplier supplier = new Supplier(supId, supName, contact, location);

        try {
            boolean isSaved = SupplierRepo.save(supplier);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "New Supplier is saved successfully...!").show();
                clearFeilds();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String supId = lblSupplierId.getText();
        String supName = txtSupplierName.getText();
        int contact = Integer.parseInt(txtContactNo.getText());
        String location = txtLocation.getText();

        Supplier supplier = new Supplier(supId, supName, contact, location);
        try {
            boolean isUpdated = SupplierRepo.update(supplier);

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Suppliers is updated successfully...!").show();
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
            Stage stage = (Stage) supplierRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFeilds(){
        txtSupplierName.setText("");
        txtContactNo.setText("");
        txtLocation.setText("");
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        try {
            Supplier supplier = SupplierRepo.searchById(id);
            if (supplier != null) {
                lblSupplierId.setText(supplier.getSupplierId());
                txtSupplierName.setText(supplier.getName());
                txtLocation.setText(supplier.getLocation());
                txtContactNo.setText(String.valueOf(supplier.getContact()));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            //throw new RuntimeException(e);
        }
    }
}
