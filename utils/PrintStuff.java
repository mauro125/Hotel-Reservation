package utils;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;

import java.util.Collection;

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

    public static String displayAvailableRooms(Collection<IRoom> currentDateRooms, Collection<IRoom> futureRooms) {
        String hasRooms = "No rooms";
        if (currentDateRooms.size() == 0 && futureRooms.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\tNo rooms Available to book or 7 days ahead");
            System.out.println("\t\t\t\t\tBack to main menu..");
            System.out.println("********************************************************\n\n");
        } else if (currentDateRooms.size() == 0 && futureRooms.size() > 0) {
            hasRooms = "future rooms";
            System.out.println("No rooms were available to book for the current date");
            System.out.println("Available rooms for 7 days ahead of your original search dates");
            System.out.println("are displayed below..");
            System.out.println("\t\t\t\tAvailable rooms");
            System.out.println("------------------------------------------------------");
            System.out.println("Room Number\t\tRoom Price\t\tRoom Type");
            System.out.println("------------------------------------------------------");
            for (Object room : futureRooms) {
                System.out.println(room);
            }
            System.out.println("\n");
        } else {
            hasRooms = "current rooms";
            System.out.println("\t\t\t\tAvailable rooms");
            System.out.println("------------------------------------------------------");
            System.out.println("Room Number\t\tRoom Price\t\tRoom Type");
            System.out.println("------------------------------------------------------");
            for (Object room : currentDateRooms) {
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
