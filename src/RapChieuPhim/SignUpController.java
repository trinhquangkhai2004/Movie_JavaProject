package RapChieuPhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.UUID;

import dao.UserDAO;
import dto.userDTO;

public class SignUpController {

    @FXML private TextField txtHo;
    @FXML private TextField txtTen;
    @FXML private TextField txtUserName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private PasswordField txtPass;
    @FXML private Button btnSignUp;

    private UserDAO userDAO = new UserDAO();
    // Xử lý khi bấm nút Đăng Ký
    @FXML
    public void handleRegister(ActionEvent event) {
        // 1. Lấy dữ liệu
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String userName = txtUserName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String pass = txtPass.getText();

        // 2. Kiểm tra dữ liệu trống
        if (ho.isEmpty() || ten.isEmpty() || email.isEmpty() || pass.isEmpty() || userName.isEmpty()) {
            showAlert("Thiếu thông tin", "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        String fullName = ho.trim() + " " + ten.trim();

        userDTO newUser = userDTO.builder()
        			.id(UUID.randomUUID())
				.fullname(fullName)
				.username(userName)
				.email(email)
				.phone(phone)
				.password(pass)
				.build();
        // 3. (Giả lập) Lưu vào Database thành công
        int result = userDAO.addUser(newUser);
		if (result == 0) {
			showAlert("Lỗi đăng ký", "Đăng ký không thành công. Vui lòng thử lại.");
			return;
		} else {
			System.out.println("Đăng ký thành công user: " + email);
	        showAlert("Thành công", "Tạo tài khoản thành công! Vui lòng đăng nhập.");
		}

        // 4. Chuyển về trang Login
        switchScene("Login.fxml");
    }

    // Xử lý khi bấm nút "Đăng nhập" ở dưới cùng để quay lại
    @FXML
    public void handleBackToLogin(MouseEvent event) {
    	try {
            // 1. Load lại file LoginView.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            // 2. Lấy cửa sổ hiện tại
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // 3. Chuyển cảnh
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi: Không tìm thấy file Login.fxml");
        }
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