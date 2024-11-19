package wekk2;

public abstract class Account {
    protected String username;
    protected String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    // Abstract method for authentication, to be implemented by subclasses
    public abstract boolean authenticate(String password);
}

