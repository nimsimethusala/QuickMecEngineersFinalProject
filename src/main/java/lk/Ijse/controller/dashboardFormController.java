package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class dashboardFormController {

    @FXML
    private AnchorPane dashboardRoot;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblJobCount;

    @FXML
    private AnchorPane Root;

    @FXML
    private TableColumn<?, ?> tblCustomerName;

    @FXML
    private TableView<?> tblDashboard;

    @FXML
    private TableColumn<?, ?> tblJobID;

    @FXML
    private TableColumn<?, ?> tblLocation;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) dashboardRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDefectOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/defectForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) dashboardRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/employeeForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) dashboardRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) dashboardRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewJobOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/jobForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) dashboardRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSparesOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/spareForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) dashboardRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) dashboardRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) Root.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
