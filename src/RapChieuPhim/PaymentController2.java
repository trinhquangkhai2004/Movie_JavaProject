package RapChieuPhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class PaymentController2 {
	@FXML private Label lblMovie;
    @FXML private Label lblSeats;
    @FXML private Label lblTotal;
    @FXML private Label lblCinemaName;
    @FXML private Label lblTicketCode;

    // Nhận dữ liệu từ BookingController
    public void setPaymentInfo(String movie, String seats, String price) {
        lblMovie.setText("Phim: " + movie);
        lblSeats.setText("Ghế: " + seats);
        lblTotal.setText(price);
    }

    @FXML
    public void handlePay(ActionEvent event) {
    	String movieNameRaw = lblMovie.getText(); // Ví dụ: "Phim: Dune Part Two"
        String seatRaw = lblSeats.getText();      // Ví dụ: "Ghế: H8, H9"
        
        // Làm sạch chuỗi (Tùy chọn)
        String movieName = movieNameRaw.replace("Phim: ", "");
        String seat = seatRaw.replace("Ghế: ", "");

        // 2. Tạo đối tượng Ticket (Giả sử bạn đã có class Ticket và SessionData như bài trước)
        // Các thông tin ngày giờ, rạp nếu không có trên màn hình này thì bạn cần truyền từ trang trước sang
        // Hoặc tạm thời hardcode (gán cứng) để test
        Ticket veMoi = new Ticket(
            movieName,              // Tên phim
            "19:30 - 02/12/2025",   // Giờ chiếu (Cần lấy từ BookingController truyền sang)
            seat,                   // Ghế
            "DUT Cinema, Đà Nẵng",  // Rạp
            "CODE-" + System.currentTimeMillis() // Tạo mã vé ngẫu nhiên
        );

        // 3. QUAN TRỌNG: Lưu vào biến toàn cục SessionData
        SessionData.currentTicket = veMoi;
        System.out.println("Đã lưu vé vào SessionData!"); // Dòng này để debug xem đã chạy chưa

        // 4. Thông báo và chuyển trang
        showAlert("Thanh toán thành công!", "Vé đã được gửi vào hòm thư của bạn.");
        goHome(event);
    }

    @FXML
    public void goBack(ActionEvent event) {
    	System.out.println("Quay lại trang trước");
    }
    
    private void goHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
