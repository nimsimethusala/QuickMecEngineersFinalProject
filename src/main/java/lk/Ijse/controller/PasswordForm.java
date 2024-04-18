package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.db.DB;

import java.io.IOException;

public class PasswordForm extends DB{

    @FXML
    private AnchorPane Root;

    @FXML
    private ImageView rootAnchorpane;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtNewPassword;

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String confirmPassword = txtConfirmPassword.getText();
        String newPassword = txtNewPassword.getText();

        DB db = new DB();
        boolean change = db.changePassword(confirmPassword,newPassword);

        if (change){
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {

        }
    }
}
