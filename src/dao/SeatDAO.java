package dao;

import dto.Seat;

public class SeatDAO {
	public void addSeat(Seat seat) {
		String sql = "INSERT INTO Seats (hall_id, row_char, seat_number) VALUES (?, ?, ?)";
		try (var conn = DataBaseInteraction.DBConnection.getConnection();
			var pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, seat.getHall_id());
			pstmt.setString(2, seat.getSeatRow().toString());
			pstmt.setInt(3, seat.getSeatNumber());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
