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
import lk.Ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordForm {

    @FXML
    private AnchorPane Root;

    @FXML
    private AnchorPane rootAnchorpane;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtNewPassword;

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String cpw = txtConfirmPassword.getText();
        String npw = txtNewPassword.getText();

        boolean isUpdate = update(cpw, npw);

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"New password is updated...!").show();
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) rootAnchorpane.getScene().getWindow();
                stage.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            txtConfirmPassword.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            new animatefx.animation.Shake(txtConfirmPassword).play();
        }
    }

    private boolean update(String cpw, String npw) {
        String sql = "UPDATE credential SET password = ? WHERE username = 'nimsi'";

        try {
            if (npw.equals(cpw)){
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, cpw);

                return pstm.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void btnBackButtonOnAction(MouseEvent mouseEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) rootAnchorpane.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
