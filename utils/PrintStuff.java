package utils;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import service.customer.CustomerService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrintStuff {
    public static void displayCustomers(Collection<Customer> customers) {
        if (customers.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\tNo customers found");
            System.out.println("********************************************************\n");
        } else {
            System.out.println("\t\t\t\tall customers");
            System.out.println("------------------------------------------------------");
            System.out.println("First Name\t\tLast Name\t\tEmail");
            System.out.println("------------------------------------------------------");
            for (Object customer : customers) {
                System.out.println(customer);
            }
        }
    }

    public static boolean displayAvailableRooms(Collection<IRoom> rooms) {
        boolean hasRooms = false;
        if (rooms.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\tNo rooms Available to book");
            System.out.println("\t\t\t\t\tBack to main menu..");
            System.out.println("********************************************************\n\n");
        } else {
            hasRooms = true;
            System.out.println("\t\t\t\tAvailable rooms");
            System.out.println("------------------------------------------------------");
            System.out.println("Room Number\t\tRoom Price\t\tRoom Type");
            System.out.println("------------------------------------------------------");
            for (Object room : rooms) {
                System.out.println(room);
            }
            System.out.println("\n");
        }

        return hasRooms;
    }

    public static void displayAllReservations(Collection<Reservation> reservations) {
        if (reservations.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\tNo reservations found");
            System.out.println("********************************************************\n");
        } else {
            System.out.println("\t\t\t\tAll Reservations");
            for (Object reservation : reservations) {
                System.out.println("------------------------------------------------------\n");
                System.out.println(reservation);
                System.out.println("\n------------------------------------------------------\n");
            }
        }
    }

    public static void displayCustomerReservations(Collection<Reservation> reservations) {
        System.out.println("\t\t\t\tYour Reservations");
        for (Object reservation : reservations) {
            System.out.println("------------------------------------------------------\n");
            System.out.println(reservation);
            System.out.println("\n------------------------------------------------------\n");
        }
    }
}
