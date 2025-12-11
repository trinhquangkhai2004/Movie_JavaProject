package RapChieuPhim;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class MyTicketController {

	@FXML private Label lblNoTicket; // Dòng chữ "Chưa có vé"
    @FXML private VBox ticketCard;   // Cái khung vé

    // Các label thông tin trên vé
    @FXML private Label lblMovieName;
    @FXML private Label lblCinema;
    @FXML private Label lblTime;
    @FXML private Label lblSeat;
    @FXML private Label lblTicketCode;

    public void initialize() {
    	if (SessionData.currentTicket != null) {
            // --- TRƯỜNG HỢP CÓ VÉ ---
            Ticket ticket = SessionData.currentTicket;

            // 1. Đổ dữ liệu
            lblMovieName.setText(ticket.getMovieName());
            // Lưu ý: Code cũ bạn dùng ticket.getCinemaName() nhưng gán vào lblCinema (kiểm tra lại tên biến)
            lblCinema.setText(ticket.getCinemaName());
            lblTime.setText(ticket.getTime());
            lblSeat.setText(ticket.getSeat());
            lblTicketCode.setText(ticket.getTicketCode());

            // 2. HIỆN thẻ vé, ẨN thông báo "chưa mua vé"
            ticketCard.setVisible(true);
            ticketCard.setManaged(true);

            lblNoTicket.setVisible(false);
            lblNoTicket.setManaged(false); // Ẩn hoàn toàn để không chiếm chỗ

        } else {
            // --- TRƯỜNG HỢP CHƯA CÓ VÉ ---

            // 1. ẨN thẻ vé, HIỆN thông báo
            ticketCard.setVisible(false);
            ticketCard.setManaged(false);

            lblNoTicket.setVisible(true);
            lblNoTicket.setManaged(true);
        }
    }

    public void displayTicket(String movie, String seats, String time, String cinemaName, String ticketCode) {
        // 1. Ẩn thông báo, Hiện vé
        lblNoTicket.setVisible(false);
        ticketCard.setVisible(true);
        ticketCard.setManaged(true);

        // 2. Điền dữ liệu vào vé
        lblMovieName.setText(movie);
        lblSeat.setText(seats);
        lblTime.setText(time); // Ví dụ: 19:30 - Hôm nay
        lblCinema.setText("DUT Cinema, Đà Nẵng"); // Hardcode rạp
        lblTicketCode.setText(ticketCode);
    }

	// Nút Back ở góc trên (Quay về trang chủ hoặc trang trước đó)
    @FXML
    public void goBack(ActionEvent event) {
        switchScene(event, "HomeView.fxml");
    }

    // --- CÁC NÚT ĐIỀU HƯỚNG MENU DƯỚI ---
    @FXML
    public void goHome(MouseEvent event) {
        switchScene(event, "HomeView.fxml");
    }

    @FXML
    public void goMovies(MouseEvent event) {
        switchScene(event, "MovieView.fxml");
    }

    @FXML
    public void goProfile(MouseEvent event) {
        switchScene(event, "ProfileView.fxml");
    }

    // Hàm chuyển cảnh đa năng (Hỗ trợ cả MouseEvent và ActionEvent)
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
            System.out.println("Lỗi: Không tìm thấy file " + fxmlFile);
        }
    }
}
