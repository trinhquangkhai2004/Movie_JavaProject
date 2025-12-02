package RapChieuPhim;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MovieDetailController {
	@FXML private Label lblMovieTitle;
    @FXML private HBox dateContainer;
    @FXML private FlowPane timeContainer2D;
    @FXML private FlowPane timeContainerIMAX;

    private String currentSelectedDate = "";

    public void initialize() {
        generateDates();
        generateTimes(); // Giả lập hiển thị giờ cho ngày đầu tiên
    }
    
    // Hàm này để trang trước truyền tên phim vào
    public void setMovieData(String title) {
        lblMovieTitle.setText(title);
    }

    private void generateDates() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dayFmt = DateTimeFormatter.ofPattern("dd/MM");
        DateTimeFormatter weekFmt = DateTimeFormatter.ofPattern("EEE", new Locale("vi", "VN"));

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            String dateStr = date.format(dayFmt);
            
            Button btn = new Button(date.format(weekFmt) + "\n" + dateStr);
            btn.setPrefSize(70, 60);
            
            // Mặc định chọn ngày đầu
            if (i == 0) {
                btn.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
                currentSelectedDate = dateStr;
            } else {
                btn.setStyle("-fx-background-color: #333; -fx-text-fill: #aaa; -fx-background-radius: 8; -fx-cursor: hand;");
            }
            
            // Logic chọn ngày (đổi màu) - Bạn có thể phát triển thêm
            btn.setOnAction(e -> {
               // Reset style các nút khác và set style nút này...
               // Load lại giờ chiếu tương ứng...
            });
            
            dateContainer.getChildren().add(btn);
        }
    }

    private void generateTimes() {
        // Giả lập dữ liệu giờ chiếu
        String[] times2D = {"09:30", "11:00", "13:15", "15:45", "18:00", "20:30", "22:45"};
        String[] timesIMAX = {"19:15", "21:00"};

        addTimeButtons(timeContainer2D, times2D);
        addTimeButtons(timeContainerIMAX, timesIMAX);
    }

    private void addTimeButtons(FlowPane container, String[] times) {
        for (String time : times) {
            Button btn = new Button(time);
            btn.setPrefSize(80, 40);
            btn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 5; -fx-border-color: #ccc; -fx-cursor: hand;");
            
            // Hover effect
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-cursor: hand;"));
            btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 5; -fx-border-color: #ccc;"));

            // SỰ KIỆN QUAN TRỌNG: BẤM GIỜ -> CHUYỂN SANG ĐẶT GHẾ
            btn.setOnAction(e -> goToSeatSelection(time));
            
            container.getChildren().add(btn);
        }
    }

    private void goToSeatSelection(String selectedTime) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingView.fxml"));
            Parent root = loader.load();
            
            // Truyền dữ liệu sang trang ghế
            BookingController controller = loader.getController();
            controller.setBookingInfo(lblMovieTitle.getText(), currentSelectedDate, selectedTime);
            
            Stage stage = (Stage) lblMovieTitle.getScene().getWindow();
            stage.setScene(new Scene(root));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MoviesView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
