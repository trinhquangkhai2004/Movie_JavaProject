package util;


public class SessionContext {
	private static String loggedEmailOrUsername;
	private static int selectedThearterId = 1;
	private static int selectedHall = 1;
	private static int selectedMovieId = 1;
	private static int selectedShowtimeId = 1;
	private static SessionContext ins;

	private SessionContext() {}

	public static SessionContext getInstance() {
		if (ins == null) {
			ins = new SessionContext();
		}
		return ins;
	}

	public static void setlogged(String EoU) {
		loggedEmailOrUsername = EoU;
	}

	public String getlogged() {
		return loggedEmailOrUsername;
	}

	public int getSelectedThearterId() {
		return selectedThearterId;
	}

	public void setSelectedThearterId(int thearterId) {
		selectedThearterId = thearterId;
	}

	public int getSelectedHall() {
		return selectedHall;
	}

	public void setSelectedHall(int hall) {
		selectedHall = hall;
	}

	public int getSelectedMovieId() {
		return selectedMovieId;
	}

	public void setSelectedMovieId(int movieId) {
		selectedMovieId = movieId;
	}

	public int getSelectedShowtimeId() {
		return selectedShowtimeId;
	}

	public void setSelectedShowtimeId(int showtimeId) {
		selectedShowtimeId = showtimeId;
	}
	public void quitSession() {
		loggedEmailOrUsername = null;
		selectedThearterId = 1;
		selectedHall = 0;
		selectedMovieId = 0;
		selectedShowtimeId = 0;
	}
}
