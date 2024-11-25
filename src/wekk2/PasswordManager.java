package wekk2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordManager {

    // Private constructor to prevent instantiation
    private PasswordManager() {}

    // Hashes a password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: Unable to hash password", e);
        }
    }

    // Verifies a password by comparing the hash of the input with the stored hash
    public static boolean verifyPassword(String enteredPassword, String storedHash) {
        return hashPassword(enteredPassword).equals(storedHash);
    }
}
