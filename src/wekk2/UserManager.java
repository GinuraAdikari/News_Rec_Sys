package wekk2;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class UserManager {
  //  private Map<String, User> usernameMap = new HashMap<>(); // Maps usernames to User objects
  //  private Map<String, User> emailMap = new HashMap<>();    // Maps emails to User objects

    // Register a new user
    public boolean registerAccount(User user) {
        String sql = "INSERT INTO users (username, email, hashed_password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getHashedPassword());
            stmt.executeUpdate();

            System.out.println("User registered successfully.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error: Unable to register user. " + e.getMessage());
            return false;
        }
    }

    // Login using either username or email
    public boolean loginUser(String identifier, String password) {
        String sql = "SELECT hashed_password FROM users WHERE username = ? OR email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, identifier);
            stmt.setString(2, identifier);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("hashed_password");
                if (PasswordManager.verifyPassword(password, storedHash)) {
                    System.out.println("Login successful for user: " + identifier);
                    return true;
                }
            }
            System.out.println("Invalid username/email or password.");
            return false;
        } catch (SQLException e) {
            System.err.println("Error: Unable to authenticate user. " + e.getMessage());
            return false;
        }
    }

    // Login using either username or email
    public boolean loginAdmin(String identifier, String password) {
        String sql = "SELECT hashed_password FROM admins WHERE admin_name = ? OR email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, identifier);
            stmt.setString(2, identifier);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("hashed_password");
                if (PasswordManager.verifyPassword(password, storedHash)) {
                    System.out.println("Login successful for user: " + identifier);
                    return true;
                }
            }
            System.out.println("Invalid username/email or password.");
            return false;
        } catch (SQLException e) {
            System.err.println("Error: Unable to authenticate user. " + e.getMessage());
            return false;
        }
    }

}
