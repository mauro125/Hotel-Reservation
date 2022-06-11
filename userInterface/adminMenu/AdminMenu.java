package userInterface.adminMenu;

import model.customer.Customer;
import resources.AdminResource;

import java.util.Collection;
import java.util.Scanner;

import static userInterface.adminMenu.AddRoom.addRoom;
import static utils.PrintStuff.displayCustomers;

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
//                        Collection<Customer> customers =  adminResource.getAllCustomers();
//                        displayCustomers(customers);
                        displayCustomers(adminResource.getAllCustomers());
//                        for (Customer customer : customers) {
//                            System.out.println(customer.toString());
//                        }
                    }
                    case 2 -> {
                        adminResource.displayAllRooms();
                    }
                    case 3 -> {
                        System.out.println("See all reservations");
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


