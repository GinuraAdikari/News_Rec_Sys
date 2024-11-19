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
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    signUpUser(scanner);
                    break;
                case 2:
                    signInAdmin(scanner);
                    break;
                case 3:
                    logIn(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void signUpUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        userManager.registerAccount(new User(username, email, password));
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
