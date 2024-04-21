package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.model.Customer;
import lk.Ijse.repository.CustomerRepo;

import java.io.IOException;

public class customerFormController {

    @FXML
    private AnchorPane CustomerRoot;

    @FXML
    private TableColumn<?, ?> tblAddress;

    @FXML
    private TableColumn<?, ?> tblContact;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TableColumn<?, ?> tblCustomerId;

    @FXML
    private TableColumn<?, ?> tblCustomerName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerNumber;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String cusId = txtCustomerId.getText();

        boolean isDelete = CustomerRepo.delete(cusId);

        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Customer is Deleted!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String cusId = txtCustomerId.getText();
        String cusName = txtCustomerName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtCustomerNumber.getText());

        Customer customer = new Customer(cusId, cusName, address, contact);

        boolean isSaved = CustomerRepo.save(customer);

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION, "New Customer is Saved....!").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String cusId = txtCustomerId.getText();
        String cusName = txtCustomerName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtCustomerNumber.getText());

        Customer customer = new Customer(cusId, cusName, address, contact);

        boolean isUpdate = CustomerRepo.update(customer);

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION, "Customer is Updated....!").show();
        }
    }

    @FXML
    void imgBackOnAction(MouseEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) CustomerRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();

        Customer customer = CustomerRepo.searchById(id);
        if (customer != null) {
            txtCustomerId.setText(customer.getId());
            txtCustomerName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtCustomerNumber.setText(String.valueOf(customer.getTel()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtCustomerNumber.setText("");
    }
}
