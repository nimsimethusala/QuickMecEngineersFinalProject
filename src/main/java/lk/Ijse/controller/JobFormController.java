package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.model.Job;
import lk.Ijse.model.JobDetail;
import lk.Ijse.model.PlaceJob;
import lk.Ijse.model.tm.JobTm;
import lk.Ijse.repository.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public Label lblJobId;

    @FXML
    private AnchorPane JobRoot;

    @FXML
    private Label lblCount;

    @FXML
    private JFXComboBox lblCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox<?> lblDefectId;

    @FXML
    private JFXComboBox lblItemId;

    @FXML
    private Label lblJobDate;

    @FXML
    private Label lblName;

    @FXML
    private JFXComboBox lblSpareId;

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
    private TableView<JobTm> tblJob;

    @FXML
    private TableColumn<?, ?> colJobId;

    @FXML
    private TableColumn<?, ?> colSpareId;

    @FXML
    private TableColumn<?, ?> colVehicleModel;

    private ObservableList<JobTm> obList = FXCollections.observableArrayList();

    private void setCellValueFactory(){

    }

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

    private void getCustomerId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = CustomerRepo.getId();

            for (String code : idList) {
                obList.add(code);
            }
            lblCustomerId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getItemId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = ItemRepo.getId();

            for (String code : idList) {
                obList.add(code);
            }
            lblItemId.setItems(obList);

        } catch (SQLException e) {
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
            lblSpareId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getDefectId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = SpareRepo.getId();

            for (String code : idList) {
                obList.add(code);
            }
            //lblSpareId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentId = JobRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            lblJobId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("O");
            int idNum = Integer.parseInt(split[1]);
            return "O" + ++idNum;
        }
        return "O1";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblJobDate.setText(String.valueOf(now));
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
        String jobId = lblJobId.getText();
        String itemId = (String) lblItemId.getValue();
        Date date = Date.valueOf(lblJobDate.getText());
        String cusId = (String) lblCustomerId.getValue();
        String model = lblVehicleModel.getText();
        String spareId = (String) lblSpareId.getValue();
        String defectId = (String) lblDefectId.getValue();
        int count = Integer.parseInt(lblCount.getText());

        try {
            String itemName = ItemRepo.getName(itemId);
            String cusName = CustomerRepo.getName(cusId);
            String spareName = SpareRepo.getName(spareId);
            String desc = DefectRepo.getDescription(defectId);

            if (itemName.equals(null)){
                btnNewItemOnAction(actionEvent);
            }

            if (cusName.equals(null)){
                btnNewCustomerOnAction(actionEvent);
            }

            if (spareName.equals(null)){
                btnNewSpareOnAction(actionEvent);
            }

            if (desc.equals(null)){
                btnNewDefectOnAction(actionEvent);
            }

            JobTm jobTm = new JobTm(jobId, date, model, cusName, itemName, count, spareName, desc);
            obList.add(jobTm);

            tblJob.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPlaceJobOnAction(ActionEvent actionEvent) {
        String jobId = lblJobId.getText();
        String itemId = (String) lblItemId.getValue();
        Date date = Date.valueOf(lblJobDate.getText());
        String cusId = (String) lblCustomerId.getValue();
        String model = lblVehicleModel.getText();
        String spareId = (String) lblSpareId.getValue();
        String defectId = (String) lblDefectId.getValue();
        int count = Integer.parseInt(lblCount.getText());

        Job job = new Job(jobId, model, date, cusId, defectId, count, spareId);
        List<JobDetail> list = new ArrayList<>();

        for (int i = 0; i < tblJob.getItems().size(); i++){
            JobTm tm = obList.get(i);

            JobDetail jobDetail = new JobDetail(itemId, tm.getItemCount(), tm.getVehicleModel(), jobId);
            list.add(jobDetail);
        }

        PlaceJob placeJob = new PlaceJob(job, list);
        try {
            boolean isPlaced = PlaceJobRepo.placeOrder(placeJob);

            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "New Job is placed Successfully!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Job Placed Unsuccessfully!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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
