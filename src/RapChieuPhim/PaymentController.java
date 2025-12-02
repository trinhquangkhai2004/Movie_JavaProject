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

public class PaymentController {

    @FXML private Label lblMovieTitle;
    @FXML private Label lblDateTime;
    @FXML private Label lblSeats;
    @FXML private Label lblTicketCount;
    @FXML private Label lblSubTotal;
    @FXML private Label lblTotal;

    // Hàm nhận dữ liệu từ trang BookingController
    public void setPaymentDetails(String movie, String dateInfo, String seats, String price) {
        lblMovieTitle.setText(movie);
        lblDateTime.setText(dateInfo);
        lblSeats.setText(seats);
        
        // Đếm số ghế dựa vào dấu phẩy (Ví dụ: "A1, A2" -> 2 vé)
        int count = seats.split(",").length;
        lblTicketCount.setText(count + " Vé phổ thông");
        
        lblSubTotal.setText(price);
        lblTotal.setText(price);
    }

    @FXML
    public void handleConfirmPayment(ActionEvent event) {
        // Sau này sẽ kết nối cổng thanh toán hoặc lưu vào DB
        showAlert("Thanh toán thành công!", "Vé đã được gửi vào tài khoản của bạn.\nCảm ơn bạn đã sử dụng dịch vụ!");
        Ticket newTicket = new Ticket(
        		"DUNE", 
        		"19:30",           // Lấy từ giờ đã chọn
                "H8, H9",          // Lấy từ ghế đã chọn
                "DUT Cinema",
                "CONFIRM-" + System.currentTimeMillis() // Tạo mã vé ngẫu nhiên
            );

            // 2. Lưu vào bộ nhớ tạm
            SessionData.currentTicket = newTicket;
        // Quay về trang chủ sau khi thanh toán xong
        goHomeAction(event); 
    }
    
    
    // Quay về trang trước
    @FXML
    public void goBack(ActionEvent event) {
        try {
            // Quay lại trang chọn ghế (BookingView)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingView.fxml"));
            Parent root = loader.load();
            
            // (Ở đây bạn có thể truyền lại dữ liệu cũ nếu muốn giữ trạng thái ghế)
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Quay về trang chủ
    public void goHomeAction(ActionEvent event) {
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
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}