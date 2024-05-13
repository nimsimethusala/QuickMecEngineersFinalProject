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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.util.Regex;
import lk.Ijse.util.TextFeildRegex;
import lk.Ijse.model.Customer;
import lk.Ijse.model.tm.CustomerTm;
import lk.Ijse.repository.CustomerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    @FXML
    public Label lblCustomerId;

    @FXML
    private AnchorPane CustomerRoot;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerNumber;

    @FXML
    private TextField txtSearch;

    private int idCounter;

    public void initialize() {
        txtCustomerName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCustomerNumber.requestFocus();
            }
        });

        txtCustomerNumber.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });

        //loadCustomerAllTel();
        getCurrentCustomerId();
        setCellValueFactory();
        loadAllCustomer();
    }

    private void getCurrentCustomerId() {
        try {
            String nextOrderId = CustomerRepo.generateNextCustomerId();
            lblCustomerId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*private void generateCustomerId() {
        String customerId = String.format("C%03d", idCounter);
        lblCustomerId.setText(customerId);
    }

    private void incrementIdCounter() {
        idCounter++;
        generateCustomerId();
    }*/

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String cusId = lblCustomerId.getText();

        try {
            boolean isDelete = CustomerRepo.delete(cusId);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer is Deleted!").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String cusId = lblCustomerId.getText();
        String cusName = txtCustomerName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtCustomerNumber.getText());

        try {
            Customer customer = new Customer(cusId, cusName, address, contact);

            boolean isSaved = CustomerRepo.save(customer);

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "New Customer is Saved....!").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String cusId = lblCustomerId.getText();
        String cusName = txtCustomerName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtCustomerNumber.getText());

        try {
            Customer customer = new Customer(cusId, cusName, address, contact);

            boolean isUpdate = CustomerRepo.update(customer);

            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION, "Customer is Updated....!").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
    /*void txtSearchOnAction(ActionEvent keyEvent) {
        String id = lblCustomerId.getText();

        try {
            Customer customer = CustomerRepo.searchById(id);
            txtSearch.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    if (customer != null) {
                        lblCustomerId.setText(customer.getId());
                        txtCustomerName.setText(customer.getName());
                        txtAddress.setText(customer.getAddress());
                        txtCustomerNumber.setText(String.valueOf(customer.getTel()));
                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "").show();
                    }
                }
            });
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/

    /*private void loadCustomerAllTel() {
        try {
            List<String> cusTel = CustomerRepo.GetCustomerTel();
            String[] possibleNames = cusTel.toArray(new String[0]);

            TextFields.bindAutoCompletion(txtSearch, possibleNames);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/

    private void clearFields() {
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtCustomerNumber.setText("");
    }

    private void loadAllCustomer(){
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.getAll();
            for (Customer customer : customerList){
                CustomerTm customerTm = new CustomerTm(customer.getId(), customer.getName(), customer.getAddress(), customer.getTel());
                obList.add(customerTm);
            }
            tblCustomer.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    public void txtCustomerNameOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.NAME,txtCustomerName);

    }
    public void txtNumberOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.CONTACT,txtCustomerNumber);
    }
}
