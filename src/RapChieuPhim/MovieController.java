package RapChieuPhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.SessionContext;

import java.io.IOException;

import dao.MovieDAO;
import dto.MovieDTO;

public class MovieController extends BaseController{

	@FXML private Label movieTitle;
	private MovieDAO dao = new MovieDAO();
	private MovieDTO movie;

	@FXML
	public void initialize() throws Exception {
	    movie = dao.getMovieById(1);
	    System.out.println(movie);
	    if (movie != null) {
			movieTitle.setText(movie.getTitle());
		} else {
			movieTitle.setText("Không tìm thấy phim");
		}
	}


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
	public void bookMovieA(MouseEvent event) {
		try {
			SessionContext.getInstance().setSelectedMovieId(1);
			switchScene(event, "MovieDetailView.fxml");
		}
		catch (Exception e) {
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
	public void goToMovies(MouseEvent event) {
		switchScene(event, "MovieView.fxml");
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
