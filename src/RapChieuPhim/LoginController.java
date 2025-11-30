package RapChieuPhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private Button btnLogin;

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = txtUser.getText();
        String password = txtPass.getText();

        // 1. Kiểm tra đăng nhập (Giả lập)
        // Sau này bạn sẽ kết nối Database ở đây
        if (username.equals("admin") && password.equals("123")) {
            System.out.println("Đăng nhập thành công!");
            switchScene("HomeView.fxml"); // Chuyển sang trang chủ
        } else {
            showAlert("Lỗi đăng nhập", "Sai tên đăng nhập hoặc mật khẩu!\n(Gợi ý: admin / 123)");
        }
    }
    
    @FXML
    public void handleSignUp() {
         System.out.println("Chuyển sang trang đăng ký...");
         switchScene("SignUp.fxml");
    }

    // Hàm chuyển cảnh
    private void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Hàm hiện thông báo lỗi
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}