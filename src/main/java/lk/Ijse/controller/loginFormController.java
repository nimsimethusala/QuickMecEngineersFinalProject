package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.db.DB;

import java.io.IOException;

public class loginFormController implements DB {

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

            boolean correct = verifyCredentials(password, username);
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

    boolean verifyCredentials(String password, String username) {
        if (password.equals(DB.password)){
            if (username.equals(DB.username)){
                return true;
            }else {
                new Alert(Alert.AlertType.ERROR, "Your Password is incorrect!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Your Username is incorrect!").show();
        }

        return false;
    }

    @FXML
    void passwordOnAction(MouseEvent event) {

    }

    @FXML
    void registerOnAction(ActionEvent event) {

    }

}
