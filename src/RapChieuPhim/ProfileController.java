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
import util.PasswordEncoder;
import util.SessionContext;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDAO;
import dto.userDTO;

public class ProfileController extends BaseController{

    @FXML private TextField txtHo;
    @FXML private TextField txtTen;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private PasswordField txtOldPass;
    @FXML private PasswordField txtNewPass;
    @FXML private PasswordField txtConfirmPass;
    private userDTO user;
    private UserDAO dao = new UserDAO();

    private void loadCurrentUser() throws SQLException {

    		user = dao.getUserInfo(SessionContext.getInstance().getlogged());
    }
    // Hàm chạy ngay khi màn hình mở lên
    public void initialize() throws SQLException {
    	    loadCurrentUser();
    	    System.out.println(user);
    		String ho = user.getFullname().split(" ")[0];
    		String ten = user.getFullname().substring(ho.length()).trim();
        // Giả lập điền sẵn thông tin user
        txtHo.setText(ho);
        txtTen.setText(ten);
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
    }

    @FXML
    public void handleSave() throws SQLException {
        // Logic lưu thông tin (Giả lập)
        String newPass = txtNewPass.getText();
        String confirmPass = txtConfirmPass.getText();

        if (!newPass.isEmpty() && !PasswordEncoder.matches(newPass, confirmPass)) {
            showAlert("Lỗi", "Mật khẩu xác nhận không khớp!");
            return;
        }
        dao.changePassword(newPass);
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

    @FXML
    public void goMovies(MouseEvent event) {
        switchScene(event, "MovieView.fxml");
    }

    @FXML
    public void goToMyTickets(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyTicketView.fxml"));
            Parent root = loader.load();

            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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