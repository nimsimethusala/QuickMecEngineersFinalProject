package lk.Ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.awt.AWTEventMulticaster.add;

public class DashboardFormController {
    @FXML
    public Label lblDate;

    @FXML
    private AnchorPane dashboardRoot;

    @FXML
    private AnchorPane Root;

    public void initialize(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateTime();
        }));

        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play();
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
            dashboardRoot.getChildren().clear();
            dashboardRoot.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDefectOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/defectForm.fxml"));
            dashboardRoot.getChildren().clear();
            dashboardRoot.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/employeeForm.fxml"));
            dashboardRoot.getChildren().clear();
            dashboardRoot.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
            dashboardRoot.getChildren().clear();
            dashboardRoot.getChildren().add(root);

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
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSparesOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/spareForm.fxml"));
            dashboardRoot.getChildren().clear();
            dashboardRoot.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
            dashboardRoot.getChildren().clear();
            dashboardRoot.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnBackOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) Root.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/paymentForm.fxml"));
            dashboardRoot.getChildren().clear();
            dashboardRoot.getChildren().add(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTime() {
        // Get current time and format it
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        // Update the label text
        lblDate.setText(formattedTime);
    }
}
