package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import DataBaseInteraction.DBConnection;
import dto.MovieDTO;
import dto.ShowtimeDTO;

public class MovieDAO {
	public MovieDTO getMovieById(int movieId) throws SQLException {
		MovieDTO movie = null;
		String sql = "select movie_id, title, description, duration_min, realease_date, country, directors.name as director_name, categories.name as category_name, actors.name as actor_name from movies join directors on movies.director_id = directors.director_id join categories on movies.category_id = categories.category_id join actors on movies.actor_id = actors.actor_id where movie_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1, movieId);
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					movie = MovieDTO.builder()
										.id(rs.getInt("movie_id"))
										.title(rs.getString("title"))
										.description(rs.getString("description"))
										.duration(rs.getInt("duration_min"))
										.releaseDate(rs.getDate("realease_date").toLocalDate())
										.country(rs.getString("country"))
										.directorName(rs.getString("director_name"))
										.categoryName(rs.getString("category_name"))
										.actorName(rs.getString("actor_name"))
										.build();
				}
			}
			return movie ;
		} catch (SQLException e) {
			System.err.println("ERROR WHEN GET USER: " + e.getMessage());
			throw e;
		}
	}

	public Map<LocalDate, List<LocalTime>> groupShowtimesByDate(List<ShowtimeDTO> showtimes) {
        Map<LocalDate, List<LocalTime>> groupedShowtimes = new HashMap<>();

        for (ShowtimeDTO showtime : showtimes) {
            LocalDateTime dateTime = showtime.getShowtime();

            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();

            List<LocalTime> timesForDate = groupedShowtimes.computeIfAbsent(date, k -> new ArrayList<>());

            timesForDate.add(time);
        }

        groupedShowtimes.forEach((date, times) -> times.sort(LocalTime::compareTo));

        return groupedShowtimes;
    }

	public List<ShowtimeDTO> getShowtimesByMovieId(int movieId) throws SQLException {
		String sql = "select * from showtimes where movie_id = ?";
		List<ShowtimeDTO> showtimes = new ArrayList<>();
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, movieId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ShowtimeDTO showtime = ShowtimeDTO.builder()
											.id(rs.getInt("showtime_id"))
											.movieId(rs.getInt("movie_id"))
											.hallId(rs.getInt("hall_id"))
											.showtime(rs.getTimestamp("start_time").toLocalDateTime())
											.price(rs.getBigDecimal("price"))
											.build();
					showtimes.add(showtime);
				}
			}
			return showtimes;
		} catch (SQLException e) {
			System.err.println("ERROR WHEN GET SHOWTIMES: " + e.getMessage());
			throw e;
		}
	}
	
	public int getShowtimeID(LocalDate date, LocalTime time, int movieId) throws SQLException {
		String sql = "SELECT showtime_id FROM showtimes WHERE DATE(start_time) = ? AND TIME(start_time) = ? AND movie_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setDate(1, java.sql.Date.valueOf(date));
			ps.setTime(2, java.sql.Time.valueOf(time));
			ps.setInt(3, movieId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("showtime_id");
				} else {
					return -1;
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR WHEN GET SHOWTIME ID: " + e.getMessage());
			throw e;
		}
	}

}
