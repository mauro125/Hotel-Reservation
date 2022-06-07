package userInterface;

import java.util.Scanner;

import static userInterface.CreateUserAccount.createAccount;

public class MainMenu {
    public static void menu() {
        boolean keepRunning = true;
        boolean firstRun = true;
        boolean fromOtherMenu = false;
        try (Scanner scanner = new Scanner(System.in)) {

            while (keepRunning) {
                try {
                    fromOtherMenu = false;
                    displayMenu(firstRun);
                    firstRun = false;
                    int selection = Integer.parseInt(scanner.nextLine());
                    System.out.println("\n");

                    switch (selection) {
                        case 1 -> {
                            System.out.println("Find and reserve a room");
                        }
                        case 2 -> {
                            System.out.println("See my reservations");
                        }
                        case 3 -> {
                            fromOtherMenu = true;
                            createAccount();
                            firstRun = true;
                        }
                        case 4 -> {
                            System.out.println("Admin");
                        }
                        case 5 -> {
                            System.out.println("Please come again!");
                            keepRunning = false;
                        }
                        default -> {
                            System.out.println("***************************************************************");
                            System.out.println("\tPlease enter a number between 1 and 4 or enter 5 to exit");
                            System.out.println("***************************************************************\n");
                        }
                    }
                } catch (Exception ex) {
                    if (!fromOtherMenu) {
                        System.out.println("\n*******************************************************");
                        System.out.println("\t\t\t\tError - Invalid Input");
                        System.out.println("*******************************************************\n");
                    }
                }
            }
        }
    }

    public static void displayMenu(boolean firstRun) {
        if (firstRun) {
            System.out.println("\n********************************************************");
            System.out.println("\t\tWelcome to Hotel Reservation Application");
            System.out.println("********************************************************");
            System.out.println("\n\t\t\t\t\t\tMain Menu");
            System.out.println("-------------------------------------------------------");
        }
        if (!firstRun) {
            System.out.println("\t\t\t\tMain Menu");
            System.out.println("-------------------------------------------------------");
        }
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-------------------------------------------------------");
        System.out.print("\nPlease enter your selection: ");
    }
}
