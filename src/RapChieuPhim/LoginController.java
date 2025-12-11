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
import util.SessionContext;

import java.io.IOException;
import java.sql.SQLException;

import dao.MovieDAO;
import dao.UserDAO;
import dto.MovieDTO;

public class LoginController {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private Button btnLogin;
    private UserDAO dao = new UserDAO();

    @FXML
    public void handleLogin(ActionEvent event) throws SQLException {
        String username = txtUser.getText();
        String password = txtPass.getText();

        if (dao.checkExistUser(username, password)) {
            System.out.println("Đăng nhập thành công!");
            SessionContext.setlogged(username);
            System.out.println("Người dùng đã đăng nhập: " + SessionContext.getInstance().getlogged());
            switchScene("HomeView.fxml");
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