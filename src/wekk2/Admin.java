package wekk2;

import java.sql.*;
import java.util.Scanner;

public class Admin extends Account {
    private String email;          // Stores the email address
    private String hashedPassword; // Securely stores the hashed password

    // Constructor
    public Admin(String username, String email, String password) {
        super(username, password);
        this.email = email;
        this.hashedPassword = PasswordManager.hashPassword(password); // Delegate hashing to PasswordManager
    }

    public static boolean deleteUser(Scanner scanner) {
        System.out.print("Enter the username of the user to delete: ");
        String username = scanner.nextLine().trim();

        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User " + username + " deleted successfully.");
                return true;
            } else {
                System.out.println("No user found with the username: " + username);
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error: Unable to delete user. " + e.getMessage());
            return false;
        }
    }
    public static void searchUser(Scanner scanner) {
        System.out.print("Enter username or email to search: ");
        String identifier = scanner.nextLine().trim();

        String sql = "SELECT username, email, age, gender, country FROM users WHERE username = ? OR email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, identifier);
            stmt.setString(2, identifier);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- User Profile ---");
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Country: " + rs.getString("country"));
            } else {
                System.out.println("No user found with the given username or email.");
            }
        } catch (SQLException e) {
            System.err.println("Error: Unable to search user. " + e.getMessage());
        }
    }

    public static void viewAllUsers() {
        String sql = "SELECT username, email, age, gender, country FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- All Users ---");
            System.out.printf("%-20s %-30s %-5s %-10s %-20s%n", "Username", "Email", "Age", "Gender", "Country");
            System.out.println("-------------------------------------------------------------------------------");

            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String country = rs.getString("country");

                System.out.printf("%-20s %-30s %-5d %-10s %-20s%n", username, email, age, gender, country);
            }
        } catch (SQLException e) {
            System.err.println("Error: Unable to retrieve all users. " + e.getMessage());
        }
    }

    public static boolean resetUserPassword(String username, String newPassword) {
        return false;
    }


    // Getter for email
    public String getEmail() {
        return email;
    }

    // Overridden authenticate method
    @Override
    public boolean authenticate(String password) {
        // Delegate password verification to PasswordManager
        return PasswordManager.verifyPassword(password, this.hashedPassword);
    }

    // Getter for hashedPassword (optional, not recommended for production use)
    public String getHashedPassword() {
        return hashedPassword;
    }
}
