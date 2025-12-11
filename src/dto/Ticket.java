package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
	private int ticketId;
	private String ticketCode;
	private String movieName;
	private String seat;
	private String hallName;
	private String hallAddress;
	private String showTime;
}
