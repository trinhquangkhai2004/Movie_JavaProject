package RapChieuPhim;

public class Ticket {
	private String movieName;
    private String time;
    private String seat;
    private String cinemaName;
    private String ticketCode;

    public Ticket(String movieName, String time, String seat, String cinemaName, String ticketCode) {
    	this.movieName = movieName;
        this.time = time;
        this.seat = seat;
        this.cinemaName = cinemaName;
        this.ticketCode = ticketCode;
    }

    // Getter methods
    public String getMovieName() {return movieName;}
    public String getTime() { return time; }
    public String getSeat() { return seat; }
    public String getCinemaName() { return cinemaName; }
    public String getTicketCode() { return ticketCode; }
}
