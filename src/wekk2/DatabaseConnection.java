package wekk2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Method to establish connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            // Ensure JDBC driver is loaded (can be replaced with a DataSource for production)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Change these to match your database credentials
            String url = "jdbc:mysql://localhost:3306/user_system";
            String username = "root";
            String password = "WweWwe@123";

            // Return a connection to the database
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Database connection failed: " + e.getMessage());
        }
    }
}
