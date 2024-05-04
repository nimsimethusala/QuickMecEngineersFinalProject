package lk.Ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.model.Employee;
import lk.Ijse.model.tm.EmployeeTm;
import lk.Ijse.repository.EmployeeRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {
    @FXML
    public TextField txtCost;

    @FXML
    private AnchorPane EmployeeRoot;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAttendance;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAttendance;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    public void initialize(){
        txtEmployeeId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmployeeName.requestFocus();
            }
        });

        txtEmployeeName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAttendance.requestFocus();
            }
        });

        txtAttendance.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtContact.requestFocus();
            }
        });

        txtContact.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });

        txtAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtSalary.requestFocus();
            }
        });

        txtSalary.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCost.requestFocus();
            }
        });

        setCellValueFactory();
        loadAllEmployees();
    }

    private void loadAllEmployees() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        try {
            List<Employee> empList = EmployeeRepo.getAll();

            for (Employee employee : empList){
                EmployeeTm employeeTm = new EmployeeTm(
                        employee.getEmpId(),
                        employee.getName(),
                        employee.getAttendance(),
                        employee.getContact(),
                        employee.getAddress(),
                        employee.getSalary()
                );
                obList.add(employeeTm);
            }

            tblEmployee.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFleids();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String empId = txtEmployeeId.getText();

        try {
            boolean isDeleted = EmployeeRepo.delete(empId);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee is deleted successfully...!").show();
                clearFleids();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String empId = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        double attendence = Double.parseDouble(txtAttendance.getText());
        int contact = Integer.parseInt(txtContact.getText());
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        double cost = Double.parseDouble(txtCost.getText());

        Employee employee = new Employee(empId, name, attendence, contact, address, salary, cost);

        try {
            boolean isSaved = EmployeeRepo.save(employee);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "New Employee is saved successfully...!").show();
                clearFleids();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String empId = txtEmployeeId.getText();
        String empName = txtEmployeeName.getText();
        double attendence = Double.parseDouble(txtAttendance.getText());
        int contact = Integer.parseInt(txtContact.getText());
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        double cost = Double.parseDouble(txtCost.getText());

        Employee employee = new Employee(empId, empName, attendence, contact, address, salary, cost);

        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee is Updated successfully...!").show();
                clearFleids();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(KeyEvent event) {

    }
    @FXML
    void imgBackOnAction(MouseEvent mouseEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) EmployeeRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFleids(){
        txtEmployeeId.setText("");
        txtEmployeeName.setText("");
        txtAttendance.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
        txtCost.setText("");
    }
}
