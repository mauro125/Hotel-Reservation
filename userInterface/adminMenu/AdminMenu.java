package userInterface.adminMenu;

import resources.AdminResource;
import java.util.Scanner;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getSingleton();
    static Scanner adminScanner = new Scanner(System.in);

    public static void adminMenu() {
        boolean adminKeepRunning = true;
            while (adminKeepRunning) {
                try {
                    displayMenu();
                    int adminSelection = Integer.parseInt(adminScanner.next());
                    System.out.println("\n");

                    switch (adminSelection) {
                        case 1 -> {
                            adminResource.getAllCustomers();
                        }
                        case 2 -> {
                            System.out.println("See my reservations");
                        }
                        case 3 -> {
                            System.out.println("adamin");
                        }
                        case 4 -> {
                            System.out.println("testing");
                        }
                        case 5 -> {
                            adminKeepRunning = false;
                        }
                        default -> {
                            System.out.println("*******************************************************************");
                            System.out.println("\tPlease enter a number between 1 and 4 or enter 5 to go back");
                            System.out.println("*******************************************************************\n");

                        }
                    }
                } catch (Exception ex) {
                    System.out.println("\n*******************************************************");
                    System.out.println("\t\t\t\tError - Invalid Input");
                    System.out.println("*******************************************************\n");
                }
            }
        }
//    }

    public static void displayMenu() {
        System.out.println("\n\n\t\t\t\tAdmin Menu");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. See all customers");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Back");
        System.out.println("-------------------------------------------------------");
        System.out.print("\nPlease enter your selection: ");
    }
}


