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
import util.SessionContext;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import dao.MovieDAO;
import dto.ShowtimeDTO;

public class MovieDetailController {
	@FXML private Label lblMovieTitle;
    @FXML private HBox dateContainer;
    @FXML private FlowPane timeContainer2D;
//    @FXML private FlowPane timeContainerIMAX;

    private Map<LocalDate, List<LocalTime>> allShowtimes;
    private MovieDAO movieDAO = new MovieDAO();

    private LocalDate currentSelectedDate = null;

    public void initialize() throws Exception {
    		List<ShowtimeDTO> showtimes = movieDAO.getShowtimesByMovieId(1);

    		allShowtimes = movieDAO.groupShowtimesByDate(showtimes);
        generateDates();
        generateTimes();
    }

    // Hàm này để trang trước truyền tên phim vào
    public void setMovieData(String title) {
        lblMovieTitle.setText(title);
    }

    private void generateDates() {
        LocalDate day = allShowtimes.keySet().iterator().next();
        DateTimeFormatter dayFmt = DateTimeFormatter.ofPattern("dd/MM");
        DateTimeFormatter weekFmt = DateTimeFormatter.ofPattern("EEE", new Locale("vi", "VN"));
        Button btn = new Button(day.format(weekFmt) + "\n" + day.format(dayFmt));
        btn.setPrefSize(70, 60);
        btn.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        dateContainer.getChildren().add(btn);
        currentSelectedDate = day;
//        for (int i = 0; i < 7; i++) {
//            LocalDate date = today.plusDays(i);
//            String dateStr = date.format(dayFmt);
//
//            Button btn = new Button(date.format(weekFmt) + "\n" + dateStr);
//            btn.setPrefSize(70, 60);
//
//            // Mặc định chọn ngày đầu
//            if (i == 0) {
//                btn.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
//                currentSelectedDate = dateStr;
//            } else {
//                btn.setStyle("-fx-background-color: #333; -fx-text-fill: #aaa; -fx-background-radius: 8; -fx-cursor: hand;");
//            }
//
//            // Logic chọn ngày (đổi màu) - Bạn có thể phát triển thêm
//            btn.setOnAction(e -> {
//               // Reset style các nút khác và set style nút này...
//               // Load lại giờ chiếu tương ứng...
//            });
//
//            dateContainer.getChildren().add(btn);
//        }
    }

    private void generateTimes() {
        // Giả lập dữ liệu giờ chiếu
//        String[] times2D = {"09:30", "11:00", "13:15", "15:45", "18:00", "20:30", "22:45"};
//        String[] timesIMAX = {"19:15", "21:00"};

        List<LocalTime> showtimeList = allShowtimes.values().iterator().next();

        addTimeButtons(timeContainer2D, showtimeList);
//        addTimeButtons(timeContainerIMAX, timesIMAX);
    }

    private void addTimeButtons(FlowPane container, List<LocalTime> times) {
    		DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        for (LocalTime time : times) {
            Button btn = new Button(time.format(tf));
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

    private void goToSeatSelection(LocalTime selectedTime) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingView.fxml"));
            Parent root = loader.load();

            // Truyền dữ liệu sang trang ghế
            SessionContext.getInstance().setSelectedShowtimeId(movieDAO.getShowtimeID(currentSelectedDate, selectedTime, SessionContext.getInstance().getSelectedMovieId()));
            BookingController controller = loader.getController();
            controller.setBookingInfo(lblMovieTitle.getText(), currentSelectedDate, selectedTime);


            Stage stage = (Stage) lblMovieTitle.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
