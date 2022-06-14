package userInterface.showUserRes;

import model.customer.Customer;
import model.reservation.Reservation;
import resources.UserResources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static utils.PrintStuff.displayCustomerReservations;

public class DisplayUserReservations {
    static public void showMyReservations() {
        final UserResources userResources = UserResources.getSingleton();
        Scanner scannerDisplayres = new Scanner(System.in);
        String input;
        String email;
        Customer customer = null;
        boolean emailFound = true;
        Collection<Reservation> reservations = new ArrayList<Reservation>();
        boolean valid = true;
        do {
            if (!valid || !emailFound) {
                if (!valid) {
                    valid = true;
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("Your email must be in the proper format \"joe@doe.com\":");
                    System.out.println("type \"back\" to go back to the main menu or try again");
                    System.out.println("-------------------------------------------------------\n");
                }
                if (!emailFound) {
                    emailFound = true;
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("\t\t\t\temail not found");
                    System.out.println("type \"back\" to go back to the main menu or try again");
                    System.out.println("-------------------------------------------------------\n");
                }
            }
            System.out.print("Enter your email address: ");
            email = scannerDisplayres.nextLine();
            if (email.equals("back")) {
                break;
            }
            if (!email.matches("^(.+)@(.+).com$")) {
                valid = false;
            } else {
                if (userResources.isEmailInSystem(email)) {
                    customer = userResources.getCustomer(email);
                    reservations = userResources.getReservations(customer);
                    displayCustomerReservations(reservations);
                    emailFound = true;
                    valid = true;
                } else {
                    emailFound = false;
                }
            }
        } while (!email.matches("^(.+)@(.+).com$") || !emailFound);
    }
}
