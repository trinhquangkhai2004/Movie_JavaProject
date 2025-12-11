package DataBaseInteraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	//Server configuration
	private static final String DB_URL = "jdbc:mysql://localhost:3306/moviedb?useSSL=false&serverTimezone=LOCAL";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Dang2004!";

    private static Connection connection = null;

    private DBConnection() {
		// Private constructor to prevent instantiation
	}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                System.out.println("Connecting...");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                System.out.println("Connect successfully!");
            } catch (SQLException e) {
                System.err.println("Can't connect to database!");
                throw e;
            }
        }
        return connection;
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection is closed.");
                    connection = null;
                }
            } catch (SQLException e) {
                System.err.println("Fail to close connection: " + e.getMessage());
            }
        }
    }
}
