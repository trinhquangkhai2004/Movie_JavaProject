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

public class SignUpController {

    @FXML private TextField txtHo;
    @FXML private TextField txtTen;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private PasswordField txtPass;
    @FXML private Button btnSignUp;

    // Xử lý khi bấm nút Đăng Ký
    @FXML
    public void handleRegister(ActionEvent event) {
        // 1. Lấy dữ liệu
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String pass = txtPass.getText();

        // 2. Kiểm tra dữ liệu trống
        if (ho.isEmpty() || ten.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            showAlert("Thiếu thông tin", "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        // 3. (Giả lập) Lưu vào Database thành công
        System.out.println("Đăng ký thành công user: " + email);
        showAlert("Thành công", "Tạo tài khoản thành công! Vui lòng đăng nhập.");

        // 4. Chuyển về trang Login
        switchScene("LoginView.fxml");
    }

    // Xử lý khi bấm nút "Đăng nhập" ở dưới cùng để quay lại
    @FXML
    public void handleBackToLogin() {
        switchScene("LoginView.fxml");
    }

    private void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) btnSignUp.getScene().getWindow(); // Lấy cửa sổ hiện tại
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}