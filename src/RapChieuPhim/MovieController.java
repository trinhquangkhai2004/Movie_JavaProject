package RapChieuPhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class MovieController extends BaseController{
	// Sự kiện quay về trang chủ (Dùng cho cả nút Back và nút Home ở dưới)
//	@FXML
//    public void goBackHome(ActionEvent event) { // Lưu ý tham số là ActionEvent vì gọi từ Button
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
//            Parent root = loader.load();
//            
//            // Lấy Stage từ sự kiện nút bấm
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            
//            stage.setScene(new Scene(root));
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Lỗi: Không tìm thấy HomeView.fxml");
//        }
//    }
	
	
	@FXML
	public void bookMovie(MouseEvent event) {
	    try {
	        // Đổi đường dẫn thành MovieDetailView.fxml
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieDetailView.fxml"));
	        Parent root = loader.load();
	        
	        // Truyền tên phim (Giả sử bạn lấy được tên phim)
	        MovieDetailController controller = loader.getController();
	        controller.setMovieData("Dune: Part Two"); 
	        
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	    } catch (IOException e) {
	        e.printStackTrace();
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
