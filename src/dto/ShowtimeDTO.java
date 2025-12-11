package dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeDTO {
	private int id;
	private int movieId;
	private int hallId;
	private LocalDateTime showtime;
	private BigDecimal price;
}
