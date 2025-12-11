package dto;

import lombok.Builder;
import lombok.Data;
import status.SeatStatus;

@Data
@Builder
public class Seat {
	private int seatId;
	private int hall_id;
	private Character seatRow;
	private int seatNumber;
//	private SeatStatus seatStatus;
}
