package RapChieuPhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookingController {

	@FXML private Label lblMovieTitle;
    @FXML private Label lblShowtimeInfo; // Label mới thêm
    @FXML private GridPane gridSeats;
    @FXML private Label lblSelectedSeats;
    @FXML private Label lblTotalPrice;

    private final double TICKET_PRICE = 90000.0;
    private String currentMovieTitle;
    private List<String> selectedSeatList = new ArrayList<>();

    public void initialize() {
        // Chỉ cần vẽ ghế thôi, không tạo ngày giờ nữa
        generateSeats();
    }
    
    // HÀM QUAN TRỌNG: Nhận thông tin từ trang MovieDetail
    public void setBookingInfo(String movieName, String date, String time) {
    	this.currentMovieTitle = movieName;
        lblMovieTitle.setText(movieName);
        lblShowtimeInfo.setText(time + " - Ngày " + date);
    }

    private void generateSeats() {
        int rows = 8;
        int cols = 10;
        gridSeats.getChildren().clear();
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char rowChar = (char) ('A' + r);
                String seatName = rowChar + String.valueOf(c + 1);
                
                ToggleButton seat = new ToggleButton(seatName);
                seat.setPrefSize(45, 45);
                
                // Style mặc định (Màu tím)
                String styleNormal = "-fx-background-color: #6a3093; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;";
                String styleSelected = "-fx-background-color: #E50914; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-weight: bold;";
                
                seat.setStyle(styleNormal);

                // XỬ LÝ CLICK GHẾ ĐỂ TÍNH TIỀN
                seat.setOnAction(e -> {
                    if (seat.isSelected()) {
                        seat.setStyle(styleSelected);
                        selectedSeatList.add(seatName); // Thêm ghế vào list
                    } else {
                        seat.setStyle(styleNormal);
                        selectedSeatList.remove(seatName); // Xóa ghế khỏi list
                    }
                    updatePaymentInfo(); // Cập nhật lại giá tiền
                });
                
                gridSeats.add(seat, c, r);
            }
        }
    }

    // Hàm cập nhật thanh thanh toán bên dưới
    private void updatePaymentInfo() {
        // 1. Cập nhật chuỗi ghế (Ví dụ: A1, A2, B5)
        if (selectedSeatList.isEmpty()) {
            lblSelectedSeats.setText("Chưa chọn ghế");
        } else {
            lblSelectedSeats.setText(String.join(", ", selectedSeatList));
        }

        // 2. Tính tổng tiền
        double total = selectedSeatList.size() * TICKET_PRICE;
        
        // Format tiền tệ (Ví dụ: 180,000 đ)
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        lblTotalPrice.setText(currencyFormat.format(total));
    }

    @FXML
    public void handleContinue(ActionEvent event) {
    	if (selectedSeatList.isEmpty()) {
            System.out.println("Chưa chọn ghế!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentView2.fxml"));
            Parent root = loader.load();
            
            // Truyền dữ liệu sang trang thanh toán
            PaymentController2 controller = loader.getController();
            controller.setPaymentInfo(currentMovieTitle, String.join(", ", selectedSeatList), lblTotalPrice.getText());
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleCancel(MouseEvent event) { // Lưu ý: MouseEvent vì là Label
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieDetailView.fxml"));
            Parent root = loader.load();
            
            // TRẢ LẠI TÊN PHIM CHO TRANG CHI TIẾT
            MovieDetailController controller = loader.getController();
            controller.setMovieData(this.currentMovieTitle);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    @FXML
    public void goBack(ActionEvent event) {
        try {
            // Nút Back giờ sẽ quay lại trang Chi tiết phim (MovieDetail)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieDetailView.fxml"));
            Parent root = loader.load();
            
            // Truyền lại tên phim để trang kia hiển thị đúng
            MovieDetailController controller = loader.getController();
            controller.setMovieData(lblMovieTitle.getText());
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}