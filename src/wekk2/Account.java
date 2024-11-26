package wekk2;

public abstract class Account {
    protected String username;
    protected String hashedPassword;

    public Account(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword; // Pass already-hashed passwords
    }


    public String getUsername() {
        return username;
    }

    // Abstract method for authentication, to be implemented by subclasses
    public abstract boolean authenticate(String password);
}

