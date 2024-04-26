package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class JobFormController {
    @FXML
    public TableColumn colItemName;

    @FXML
    public TableColumn colItemCount;

    @FXML
    public TableColumn colSpareName;

    @FXML
    public TableColumn colDefect;

    @FXML
    public Label lblJobI;

    @FXML
    public TableColumn colPayment;

    @FXML
    public Label lblJobId;

    @FXML
    public Label lblPayment;

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
    private Label lblName;

    @FXML
    private JFXComboBox<?> lblSpareId;

    @FXML
    private Label lblVehicleModel;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDefectId;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableView<?> tblJob;

    @FXML
    private TableColumn<?, ?> colJobId;

    @FXML
    private TableColumn<?, ?> colSpareId;

    @FXML
    private TableColumn<?, ?> colVehicleModel;

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewDefectOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/defectForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewItemOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewSpareOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/spareForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    public void btnAddToJobOnAction(ActionEvent actionEvent) {

    }

    public void btnPlaceJobOnAction(ActionEvent actionEvent) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
