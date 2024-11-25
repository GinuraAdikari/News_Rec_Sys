package wekk2;


import java.util.Scanner;

public class ValidationUtility {

    // Validate and prompt for username
    public static String validateAndPromptUsername(Scanner scanner) {
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            if (!username.isEmpty() && username.matches("^[a-zA-Z0-9_]+$")) {
                return username;
            } else {
                System.out.println("Invalid username. Please use only alphanumeric characters and underscores.");
            }
        }
    }

    // Validate and prompt for email
    public static String validateAndPromptEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();
            if (email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$")) {
                return email;
            } else {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }
    }

    // Validate and prompt for password
    public static String validateAndPromptPassword(Scanner scanner) {
        while (true) {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (password.length() >= 8 && password.matches(".*[A-Z].*") &&
                    password.matches(".*[a-z].*") && password.matches(".*\\d.*")) {
                return password;
            } else {
                System.out.println("Password must be at least 8 characters long, contain an uppercase letter, a lowercase letter, and a number.");
            }
        }
    }

    // Validate and prompt for age
    public static int validateAndPromptAge(Scanner scanner) {
        while (true) {
            System.out.print("Enter age: ");
            String ageInput = scanner.nextLine();
            try {
                int age = Integer.parseInt(ageInput);
                if (age > 0) {
                    return age;
                } else {
                    System.out.println("Age must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a valid number.");
            }
        }
    }

    // Validate and prompt for gender
    public static String validateAndPromptGender(Scanner scanner) {
        while (true) {
            System.out.print("Enter gender (Male/Female/Other): ");
            String gender = scanner.nextLine().trim();
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") ||
                    gender.equalsIgnoreCase("Other")) {
                return gender;
            } else {
                System.out.println("Invalid gender. Please enter Male, Female, or Other.");
            }
        }
    }

    // Validate and prompt for country
    public static String validateAndPromptCountry(Scanner scanner) {
        while (true) {
            System.out.print("Enter country: ");
            String country = scanner.nextLine().trim();
            if (country.matches("^[a-zA-Z0-9-]+$")) {
                return country;
            } else {
                System.out.println("Invalid country name. Please use only alphanumeric characters and hyphens.");
            }
        }
    }
}
