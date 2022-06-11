package utils;

import model.customer.Customer;
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

    public static void displayAvailableRooms(Collection<IRoom> rooms) {
        if (rooms.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\tNo rooms Available to book");
            System.out.println("********************************************************\n");
        } else {
            System.out.println("\t\t\t\tAvailable rooms");
            System.out.println("------------------------------------------------------");
            System.out.println("Room Number\t\tRoom Price\t\tRoom Type");
            System.out.println("------------------------------------------------------");
            for (Object room : rooms) {
                System.out.println(room);
            }
            System.out.println("\n");
        }
    }
}
