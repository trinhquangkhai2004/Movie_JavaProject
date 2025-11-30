package RapChieuPhim;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class ProfileController extends BaseController{

    @FXML private TextField txtHo;
    @FXML private TextField txtTen;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private PasswordField txtOldPass;
    @FXML private PasswordField txtNewPass;
    @FXML private PasswordField txtConfirmPass;

    // Hàm chạy ngay khi màn hình mở lên
    public void initialize() {
        // Giả lập điền sẵn thông tin user
        txtHo.setText("Nguyễn");
        txtTen.setText("Văn Admin");
        txtEmail.setText("admin@dut.udn.vn");
        txtPhone.setText("0905123456");
    }

    @FXML
    public void handleSave() {
        // Logic lưu thông tin (Giả lập)
        String newPass = txtNewPass.getText();
        String confirmPass = txtConfirmPass.getText();

        if (!newPass.isEmpty() && !newPass.equals(confirmPass)) {
            showAlert("Lỗi", "Mật khẩu xác nhận không khớp!");
            return;
        }

        showAlert("Thành công", "Đã cập nhật hồ sơ của bạn!");
        
        // Reset ô mật khẩu
        txtOldPass.clear();
        txtNewPass.clear();
        txtConfirmPass.clear();
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        // Quay về trang Login
        switchScene(event, "LoginView.fxml");
    }

    @FXML
    public void goHomeAction(ActionEvent event) {
        switchScene(event, "HomeView.fxml");
    }

    // 2. Hàm dành riêng cho icon Home ở dưới (Mouse Event)
//    @FXML
//    public void goHomeMouse(MouseEvent event) {
//        switchScene(event, "HomeView.fxml");
//    }
    
    @FXML
    public void goMovies(MouseEvent event) {
        switchScene(event, "MoviesView.fxml");
    }

    // Hàm chuyển cảnh đa năng (nhận cả MouseEvent và ActionEvent)
    private void switchScene(Object event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            
            Stage stage;
            if (event instanceof MouseEvent) {
                stage = (Stage) ((Node)((MouseEvent) event).getSource()).getScene().getWindow();
            } else {
                stage = (Stage) ((Node)((ActionEvent) event).getSource()).getScene().getWindow();
            }
            
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Không tìm thấy file: " + fxmlFile);
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