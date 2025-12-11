package dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import status.BookingStatus;

@Data
@Builder
public class BookingDTO {
	private int id;
	private UUID userId;
	private int showtimeId;
	private LocalDateTime bookingTime;
	private BigDecimal totalAmount;
	private BookingStatus status;
}
