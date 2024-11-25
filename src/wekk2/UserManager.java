package wekk2;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import java.util.Scanner;

public class UserManager {
  //  private Map<String, User> usernameMap = new HashMap<>(); // Maps usernames to User objects
  //  private Map<String, User> emailMap = new HashMap<>();    // Maps emails to User objects

    // Register a new user
    public static boolean registerAccount(User user) {
        String sql = "INSERT INTO users (username, email, hashed_password, age, gender, country) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getHashedPassword());
            stmt.setInt(4, user.getAge());         // Set age
            stmt.setString(5, user.getGender());  // Set gender
            stmt.setString(6, user.getCountry()); // Set country
            stmt.executeUpdate();

            System.out.println("User registered successfully.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error: Unable to register user. " + e.getMessage());
            return false;
        }
    }

    // Helper method to update the database
    static boolean updateDatabase(String username, String column, String newValue) {
        String sql = "UPDATE users SET " + column + " = ? WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newValue);
            stmt.setString(2, username);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            } else {
                System.out.println("No user found with the username: " + username);
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error: Unable to update user detail. " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUserAccount(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Confirm with the user before deletion
            System.out.println("Are you sure you want to delete your account? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (!confirmation.equals("yes")) {
                System.out.println("Account deletion cancelled.");
                return false;
            }

            // Set the username parameter in the SQL query
            stmt.setString(1, username);

            // Execute the deletion
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Account deleted successfully for username: " + username);
                return true;
            } else {
                System.out.println("No account found with the username: " + username);
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error: Unable to delete account. " + e.getMessage());
            return false;
        }
    }

    public static void viewProfile(String username) {
        String sql = "SELECT username, email, age, gender, country, created_at FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the username parameter
            stmt.setString(1, username);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Check if the user exists and display details
            if (rs.next()) {
                System.out.println("\n--- User Profile Details ---");
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Country: " + rs.getString("country"));
                System.out.println("Account Created At: " + rs.getTimestamp("created_at"));
            } else {
                System.out.println("No user found with the username: " + username);
            }
        } catch (SQLException e) {
            System.err.println("Error: Unable to fetch user profile. " + e.getMessage());
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
