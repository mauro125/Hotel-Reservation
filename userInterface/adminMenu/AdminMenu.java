package userInterface.adminMenu;

import resources.AdminResources;

import java.util.Scanner;

import static userInterface.adminMenu.AddRoom.addRoom;
import static utils.PrintStuff.displayAllReservations;
import static utils.PrintStuff.displayCustomers;

public class AdminMenu {
    private static final AdminResources adminResources = AdminResources.getSingleton();
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
                        displayCustomers(adminResources.getAllCustomers());
                    }
                    case 2 -> {
                        adminResources.displayAllRooms();
                    }
                    case 3 -> {
                        displayAllReservations(adminResources.displayAllReservations());
                    }
                    case 4 -> {
                        addRoom();
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

    public static void displayMenu() {
        System.out.println("\n\n\t\t\t\tAdmin Menu");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("-------------------------------------------------------");
        System.out.print("\nPlease enter your selection: ");
    }
}


