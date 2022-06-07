import java.util.Scanner;

public class MainMenu {
    public static void menu() {
        boolean keepRunning = true;
        boolean firstRun = true;
        try (Scanner scanner = new Scanner(System.in)) {

            while (keepRunning) {
                try {
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
                            System.out.println("Create an Account");
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
                    System.out.println("\n*******************************************************");
                    System.out.println("\t\t\t\tError - Invalid Input");
                    System.out.println("*******************************************************\n");
                }
            }
        }
    }

    public static void displayMenu(boolean firstRun) {
        if (firstRun) {
            System.out.println("\nWelcome to Hotel Reservation Application\n");
            System.out.println("-----------------------------------------");
        }
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.print("Please enter your selection: ");
    }
}
