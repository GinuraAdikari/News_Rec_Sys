package wekk2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import static wekk2.UserManager.updateDatabase;

public class User extends Account {
    private String email;
    private int age;
    private String gender;
    private String country;
    private String hashedPassword;

    public User(String username, String email, String password, int age, String gender, String country) {
        super(username, password);
        this.hashedPassword = PasswordManager.hashPassword(password);
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    static void signUpUser(Scanner scanner) {
        String username = ValidationUtility.validateAndPromptUsername(scanner);
        String email = ValidationUtility.validateAndPromptEmail(scanner);
        String password = ValidationUtility.validateAndPromptPassword(scanner);
        int age = ValidationUtility.validateAndPromptAge(scanner);
        String gender = ValidationUtility.validateAndPromptGender(scanner);
        String country = ValidationUtility.validateAndPromptCountry(scanner);

        // Create and register the user
        User newUser = new User(username, email, password, age, gender, country);
        if (UserManager.registerAccount(newUser)) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("User registration failed.");
        }
    }

    public static boolean updateUserDetails(String username, Scanner scanner) {
        String updateOption;
        String newValue = null;
        int newAge = -1;

        System.out.println("Which detail would you like to update?");
        System.out.println("1. Username");
        System.out.println("2. Email");
        System.out.println("3. Password");
        System.out.println("4. Age");
        System.out.println("5. Gender");
        System.out.println("6. Country");
        System.out.print("Enter the number corresponding to your choice: ");
        updateOption = scanner.nextLine().trim();

        switch (updateOption) {
            case "1": // Update Username
                newValue = ValidationUtility.validateAndPromptUsername(scanner);
                updateDatabase(username, "username", newValue);
                break;

            case "2": // Update Email
                newValue = ValidationUtility.validateAndPromptEmail(scanner);
                updateDatabase(username, "email", newValue);
                break;

            case "3": // Update Password
                newValue = ValidationUtility.validateAndPromptPassword(scanner);
                // Hash the new password before updating
                newValue = PasswordManager.hashPassword(newValue);
                updateDatabase(username, "hashed_password", newValue);
                break;

            case "4": // Update Age
                newAge = ValidationUtility.validateAndPromptAge(scanner);
                updateDatabase(username, "age", Integer.toString(newAge));
                break;

            case "5": // Update Gender
                newValue = ValidationUtility.validateAndPromptGender(scanner);
                updateDatabase(username, "gender", newValue);
                break;

            case "6": // Update Country
                newValue = ValidationUtility.validateAndPromptCountry(scanner);
                updateDatabase(username, "country", newValue);
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                return false;
        }

        System.out.println("User detail updated successfully.");
        return true;
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
