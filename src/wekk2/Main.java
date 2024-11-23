package wekk2;

import java.util.Scanner;

public class Main {
    private static UserManager userManager = new UserManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Sign Up (User)");
            System.out.println("2. Sign In (Admin)");
            System.out.println("3. Log In");
            System.out.println("4. Update User Details");
            System.out.println("5. Delete User Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    User.signUpUser(scanner);  // Sign up user
                    break;
                case 2:
                    signInAdmin(scanner);  // Admin sign in
                    break;
                case 3:
                    logIn(scanner);  // User login
                    break;
                case 4:
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine().trim();
                    if (!User.updateUserDetails(username, scanner)) {
                        System.out.println("Failed to update user details. Please try again.");
                    }
                    break;
                case 5:
                    System.out.print("Enter your username: ");
                    username = scanner.nextLine().trim();
                    userManager = new UserManager();
                    if (!userManager.deleteUserAccount(username)) {
                        System.out.println("Failed to delete account. Please try again.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }


    private static void logIn(Scanner scanner) {
        System.out.println("Log in using username or email:");
        System.out.print("Enter username/email: ");
        String identifier = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        userManager.loginUser(identifier, password);
    }


    private static void signInAdmin(Scanner scanner) {
        System.out.println("Log in using username or email:");
        System.out.print("Enter username/email: ");
        String identifier = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        userManager.loginAdmin(identifier, password);
    }

}
