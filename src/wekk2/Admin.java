package wekk2;

public class Admin extends Account {
    private String email;          // Stores the email address
    private String hashedPassword; // Securely stores the hashed password

    // Constructor
    public Admin(String username, String email, String password) {
        super(username, password);
        this.email = email;
        this.hashedPassword = PasswordManager.hashPassword(password); // Delegate hashing to PasswordManager
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