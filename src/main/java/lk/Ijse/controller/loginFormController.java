package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.db.DB;


import java.io.IOException;

public class loginFormController extends DB {

    @FXML
    private AnchorPane rootLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            String password = txtPassword.getText();
            String username = txtUserId.getText();

            DB db = new DB();
            boolean correct = db.verifyCredentials(password, username);
            if (correct){
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void passwordOnAction(MouseEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/passwordForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void registerOnAction(ActionEvent event) {

    }
}
