package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.model.Customer;
import lk.Ijse.repository.CustomerRepo;
import lk.Ijse.util.Regex;
import lk.Ijse.util.TextFeildRegex;
import lk.Ijse.model.Item;
import lk.Ijse.model.tm.ItemTm;
import lk.Ijse.repository.DefectRepo;
import lk.Ijse.repository.ItemRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {
    @FXML
    public TableColumn colJobId;

    @FXML
    public JFXComboBox cmbDefectId;

    @FXML
    public Label lblItemId;
    @FXML
    public TextField txtCount;

    @FXML
    private AnchorPane itemRoot;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtItemCount;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    private int idCounter;

    public void initialize(){
        getCurrentItemId();
        getDefectId();
        setCellValueFactory();
        loadAllCustomer();
    }

    private void getCurrentItemId() {
        try {
            String nextOrderId = ItemRepo.generateNextItemId();
            lblItemId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();

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
        String itemId = lblItemId.getText();
        String name = txtName.getText();
        int itemCount = Integer.parseInt(txtCount.getText());
        String defectId = (String) cmbDefectId.getValue();

        Item item = new Item(itemId, name, itemCount, defectId);
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
        String itemId = lblItemId.getText();
        String itemName = txtName.getText();
        int itemCount = Integer.parseInt(txtCount.getText());
        String defectId = (String) cmbDefectId.getValue();

        try {
            Item item = new Item(itemId, itemName, itemCount, defectId);

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
    void txtSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            Item item = ItemRepo.searchById(id);
            if (item != null) {
                lblItemId.setText(item.getItemID());
                txtName.setText(item.getItemName());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Item not found!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        lblItemId.setText("");
        txtName.setText("");
    }

    private void getDefectId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = DefectRepo.getId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbDefectId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCustomer(){
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<Item> itemList = ItemRepo.getAll();
            for (Item item : itemList){
                ItemTm itemTm = new ItemTm(
                        item.getItemID(),
                        item.getItemName(),
                        item.getDefectId()
                );
                obList.add(itemTm);
            }
            tblItem.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory(){
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colJobId.setCellValueFactory(new PropertyValueFactory<>("defectId"));
    }

    public void txtItemNameOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.NAME,txtName);
    }
}
