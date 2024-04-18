package lk.Ijse.db;

import javafx.scene.control.Alert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DB {
    private String password = "123";
    private String username = "nimsi";

    public boolean verifyCredentials(String password, String username) {
        if (password.equals(this.password)){
            if (username.equals(this.username)){
                return true;
            }else {
                new Alert(Alert.AlertType.ERROR, "Your Password is incorrect!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Your Username is incorrect!").show();
        }

        return false;
    }

    public boolean changePassword(String confirmPassword, String newPassword){
        if (newPassword.equals(confirmPassword)){
            this.password = confirmPassword;
            return true;
        }
        return false;
    }
}
