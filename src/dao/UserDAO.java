package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import DataBaseInteraction.DBConnection;
import dto.userDTO;
import util.PasswordEncoder;
import util.SessionContext;

public class UserDAO {
	public int addUser(userDTO user) {
		String sqlString = "INSERT INTO users (id, fullname, username, email, password, phone) VALUES (UUID_TO_BIN(?), ?, ?, ?, ?, ?)";
		String uuidToInsert = user.getId().toString();
		try (Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
			pstmt.setString(1, uuidToInsert);
			pstmt.setString(2, user.getFullname());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, PasswordEncoder.hash(user.getPassword()));
			pstmt.setString(6, user.getPhone());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public boolean checkExistUser(String emailorUname, String Rawpassword) throws SQLException {
		String sql = "SELECT password FROM users WHERE email = ? OR username = ?";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setString(1, emailorUname);
		            pstmt.setString(2, emailorUname);

		            try (ResultSet rs = pstmt.executeQuery()) {

		                if (rs.next()) {
		                    String storePassString = rs.getString("password");

		                    if (PasswordEncoder.matches(Rawpassword, storePassString))
		                    		return true;
		                }
		            }

	            return false;

	        } catch (SQLException e) {
	            System.err.println("Error when check login: " + e.getMessage());
	            throw e;
	        }
	}
	public userDTO getUserInfo(String usernameOrEmail) throws SQLException {
		String sql = "SELECT BIN_TO_UUID(id), fullname, username, email, phone, password FROM users WHERE username = ? OR email = ?";
		userDTO user = new userDTO();
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, usernameOrEmail);
			ps.setString(2, usernameOrEmail);
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					user = userDTO.builder()
									.id(UUID.fromString(rs.getString(1)))
									.fullname(rs.getString(2))
									.username(rs.getString(3))
									.email(rs.getString(4))
									.phone(rs.getString(5))
									.password(rs.getString(6))
									.build();
				}
			}
			return user;
		} catch (SQLException e) {
			System.err.println("ERROR WHEN GET USER: " + e.getMessage());
			throw e;
		}
	}
	public void changePassword(String newPass, UUID userId) {
		String sql = "UPDATE users SET password = ? WHERE users.id = UUID_TO_BIN(?)";
		try (Connection connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, PasswordEncoder.hash(newPass));
            ps.setString(2, userId.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("ERROR WHEN CHANGE PASSWORD: " + e.getMessage());
		}
	}
}
