package RapChieuPhim;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import java.util.List;

public class RoomController {

    @FXML private GridPane gridSeats;
    
    // --- KHAI BÁO MÀU SẮC (Sửa màu ở đây cho nhanh) ---
    private final String COLOR_BG_ROOT = "-fx-background-color: #1a1a1a;";
    private final String STYLE_NORMAL   = "-fx-background-color: #6a3093; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;";
    private final String STYLE_HOVER    = "-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;";
    private final String STYLE_SELECTED = "-fx-background-color: #d6306d; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, #d6306d, 10, 0.3, 0, 0);";
    private final String STYLE_BOOKED   = "-fx-background-color: #444444; -fx-text-fill: #888888; -fx-background-radius: 8;";

    public void initialize() {
        // 1. Setup Lưới ghế
        gridSeats.setStyle(COLOR_BG_ROOT); // Màu nền đen cho lưới
        gridSeats.getParent().setStyle(COLOR_BG_ROOT); // Màu nền đen cho cả cửa sổ (nếu Grid nằm trong Pane khác)
        
        gridSeats.getColumnConstraints().clear();
        gridSeats.getRowConstraints().clear();
        gridSeats.setAlignment(Pos.CENTER);
        gridSeats.setHgap(10);
        gridSeats.setVgap(10);

        // 2. Vẽ ghế
        int columns = 12;
        int rows = 10;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                
                String seatName = (char)('A' + row) + String.valueOf(col + 1);
                ToggleButton btn = new ToggleButton(seatName);
                btn.setPrefSize(50, 45);
                btn.setId(seatName);

                // --- QUAN TRỌNG: Gán Style mặc định ---
                btn.setStyle(STYLE_NORMAL);

                // --- Xử lý sự kiện CLICK ---
                btn.setOnAction(e -> updateButtonStyle(btn));

                // --- Xử lý sự kiện HOVER (Di chuột) thủ công ---
                // Vì không dùng CSS :hover, ta phải dùng code Java
                btn.setOnMouseEntered(e -> {
                    if (!btn.isSelected() && !btn.isDisabled()) {
                        btn.setStyle(STYLE_HOVER); // Sáng lên
                    }
                });
                
                btn.setOnMouseExited(e -> {
                    if (!btn.isSelected() && !btn.isDisabled()) {
                        btn.setStyle(STYLE_NORMAL); // Trả về màu cũ
                    }
                });

                // --- Tạo lối đi ---
                int visualColumn = col;
                if (col >= 4) visualColumn++;
                if (col >= 8) visualColumn++;

                gridSeats.add(btn, visualColumn, row);
            }
        }
    }

    // Hàm riêng để cập nhật màu sắc dựa trên trạng thái nút
    private void updateButtonStyle(ToggleButton btn) {
        if (btn.isDisabled()) {
            btn.setStyle(STYLE_BOOKED);
        } else if (btn.isSelected()) {
            btn.setStyle(STYLE_SELECTED);
        } else {
            btn.setStyle(STYLE_NORMAL);
        }
    }

    // Hàm nhận dữ liệu ghế đã đặt từ Backend
    public void setBookedSeats(List<String> bookedSeats) {
        for (javafx.scene.Node node : gridSeats.getChildren()) {
            if (node instanceof ToggleButton) {
                ToggleButton btn = (ToggleButton) node;
                if (bookedSeats.contains(btn.getText())) {
                    btn.setDisable(true);
                    updateButtonStyle(btn); // Cập nhật ngay màu xám
                }
            }
        }
    }
}