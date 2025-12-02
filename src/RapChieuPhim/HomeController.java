package RapChieuPhim;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private StackPane poster1; // Phải trùng tên với fx:id bên FXML

    // Hàm xử lý khi bấm vào Poster
    @FXML
    public void handleMovieClick(MouseEvent event) {
        switchScene("RoomView.fxml"); // Chuyển sang màn hình ghế
    }

    // Hàm chung để chuyển đổi màn hình
    private void switchScene(String fxmlFile) {
        try {
            // 1. Load file giao diện mới
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // 2. Lấy Stage (Cửa sổ) hiện tại từ sự kiện click
            // (Mẹo: Lấy từ poster1 vì nó đang nằm trên cửa sổ đó)
            Stage stage = (Stage) poster1.getScene().getWindow();

            // 3. Thiết lập Scene mới
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            // Căn giữa lại màn hình (Optional)
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi: Không tìm thấy file " + fxmlFile);
        }
    }
 // 1. Khai báo các biến từ FXML
    @FXML private ScrollPane scrollPhim; // Nhớ thêm fx:id="scrollPhim" vào ScrollPane bên FXML nhé
    @FXML private Region  dot1, dot2, dot3;

    // 2. Định nghĩa Style (CSS) ngay trong Java cho tiện xử lý
    private final String ACTIVE_STYLE = "-fx-background-color: #E50914; -fx-min-width: 25; -fx-pref-width: 25; -fx-background-radius: 10; -fx-cursor: hand;";
    private final String INACTIVE_STYLE = "-fx-background-color: #444444; -fx-min-width: 8; -fx-pref-width: 8; -fx-background-radius: 10; -fx-cursor: hand;";

    // 3. Các hàm xử lý sự kiện Click
    @FXML
    public void showMovie1(MouseEvent event) {
        updateIndicators(dot1, 0.0); // 0.0 là đầu danh sách
    }

    @FXML
    public void showMovie2(MouseEvent event) {
        updateIndicators(dot2, 0.5); // 0.5 là giữa danh sách
    }

    @FXML
    public void showMovie3(MouseEvent event) {
        updateIndicators(dot3, 1.0); // 1.0 là cuối danh sách
    }

    // 4. Hàm logic chung để đổi màu và cuộn
    private void updateIndicators(Region activeDot, double scrollPosition) {
        // Reset tất cả về màu xám
        dot1.setStyle(INACTIVE_STYLE);
        dot2.setStyle(INACTIVE_STYLE);
        dot3.setStyle(INACTIVE_STYLE);

        // Set cái được chọn thành màu đỏ & dài ra
        activeDot.setStyle(ACTIVE_STYLE);

        // Cuộn ScrollPane đến vị trí mong muốn
        // setHvalue nhận giá trị từ 0.0 (trái) đến 1.0 (phải)
        scrollPhim.setHvalue(scrollPosition);
    }
    
    @FXML
    public void goToMovies(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieView.fxml"));
            Parent root = loader.load();
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi không tìm thấy MovieView.fxml");
        }
    }
    
    @FXML
    public void goToProfile(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}