package dao;

import DataBaseInteraction.DBConnection;
import dto.BookingDTO;

public class BookingDAO {
	public void createBooking(BookingDTO dto) {
		String sql = "INSERT INTO bookings (user_id, showtime_id, book_date, totalamount) VALUES (UUID_TO_BIN(?), ?, ?, ?)";
		try (var conn = DBConnection.getConnection(); var pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, dto.getUserId().toString());
			pstmt.setInt(2, dto.getShowtimeId());
			pstmt.setObject(3, dto.getBookingTime());
			pstmt.setBigDecimal(4, dto.getTotalAmount());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
