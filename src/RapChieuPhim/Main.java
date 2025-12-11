package RapChieuPhim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Nạp file giao diện .fxml (Đổi tên file cho đúng với file của bạn)
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            // 2. Tạo Scene (cảnh phim) từ giao diện đó
            Scene scene = new Scene(root);
            primaryStage.setTitle("Quản Lý Đặt Vé Phim");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}