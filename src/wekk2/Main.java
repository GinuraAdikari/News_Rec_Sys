package wekk2;

import java.util.Scanner;

public class Main {
    private static UserManager userManager = new UserManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Sign Up (User)");
            System.out.println("2. Log In (User)");
            System.out.println("3. Sign In (Admin)");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    User.signUpUser(scanner); // User sign up
                    break;
                case 2:
                    logInUser(scanner); // Log in user
                    break;
                case 3:
                    signInAdmin(scanner); // Admin sign in
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void logInUser(Scanner scanner) {
        System.out.println("Log in using username or email:");
        System.out.print("Enter username/email: ");
        String identifier = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Authenticate user
        if (userManager.loginUser(identifier, password)) { // Ensure loginUser returns a boolean
            System.out.println("User login successful.");
            userMenu(scanner, identifier); // Show user menu after successful login
        } else {
            System.out.println("Invalid username/email or password. Please try again.");
        }
    }

    private static void signInAdmin(Scanner scanner) {
        System.out.println("Log in using username or email:");
        System.out.print("Enter username/email: ");
        String identifier = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Authenticate admin
        if (userManager.loginAdmin(identifier, password)) { // Ensure loginAdmin returns a boolean
            System.out.println("Admin login successful.");
            adminMenu(scanner); // Show admin menu after successful login
        } else {
            System.out.println("Invalid username/email or password. Please try again.");
        }
    }

    private static void userMenu(Scanner scanner, String loggedInUsername) {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Profile Details");
            System.out.println("2. Update Profile Details");
            System.out.println("3. Delete Account");
            System.out.println("4. Log Out");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    UserManager.viewProfile(loggedInUsername);
                    break;
                case 2:
                    if (!User.updateUserDetails(loggedInUsername, scanner)) {
                        System.out.println("Failed to update user details. Please try again.");
                    }
                    break;
                case 3:
                    if (userManager.deleteUserAccount(loggedInUsername)) {
                        System.out.println("Account deleted successfully.");
                        return; // Exit menu after account deletion
                    } else {
                        System.out.println("Failed to delete account. Please try again.");
                    }
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return; // Exit menu to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Users");
            System.out.println("2. Search User Profiles");
            System.out.println("3. Delete a User");
            System.out.println("4. Reset User Password");
            System.out.println("5. Log Out");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Admin.viewAllUsers();
                    break;
                case 2:
                    Admin.searchUser(scanner);
                    break;
                case 3:
                    Admin.deleteUser(scanner);
                    break;
                case 4:
                    System.out.print("Enter the username of the user to reset the password: ");
                    String username = scanner.nextLine().trim();
                    System.out.print("Enter the new password: ");
                    String newPassword = scanner.nextLine();
                    if (Admin.resetUserPassword(username, newPassword)) {
                        System.out.println("Password reset successfully.");
                    } else {
                        System.out.println("Failed to reset the password.");
                    }
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return; // Exit admin menu to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
