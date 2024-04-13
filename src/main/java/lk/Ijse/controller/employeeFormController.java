package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class employeeFormController {

    @FXML
    private AnchorPane EmployeeRoot;

    @FXML
    private TableColumn<?, ?> tblAddress;

    @FXML
    private TableColumn<?, ?> tblAttendance;

    @FXML
    private TableColumn<?, ?> tblContact;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TableColumn<?, ?> tblEmployeeId;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TableColumn<?, ?> tblSalary;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAttendance;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmployee;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmployeeIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
