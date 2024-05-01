package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.Ijse.model.Item;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.repository.ItemRepo;

import java.io.IOException;
import java.sql.SQLException;

public class ItemFormController {
    @FXML
    public TableColumn colJobId;

    @FXML
    private AnchorPane itemRoot;

    @FXML
    private TableView<?> tblItem;

    @FXML
    private TableColumn<?, ?> colItemCount;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtItemCount;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();

        try {
            boolean isDeleted = ItemRepo.delete(itemId);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Item is deleted successfully...!");
                clearFeilds();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String name = txtName.getText();
        int count = Integer.parseInt(txtItemCount.getText());

        Item item = new Item(itemId, name, count);
        try {
            boolean isSaved = ItemRepo.save(item);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "New Item is saved SuccessFully...!").show();
                clearFeilds();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String itemName = txtName.getText();
        int count = Integer.parseInt(txtItemCount.getText());

        try {
            Item item = new Item(itemId, itemName, count);

            boolean isUpdate = ItemRepo.updateItem(item);

            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION, "Item is Updated....!").show();
                clearFeilds();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtItemIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }
    @FXML
    void imgBackOnAction(MouseEvent mouseEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) itemRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFeilds(){
        txtItemId.setText("");
        txtName.setText("");
        txtItemCount.setText("");
    }
}
