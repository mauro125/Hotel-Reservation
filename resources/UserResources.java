package resources;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import service.customer.CustomerService;
import service.reservation.ReservationService;

import java.util.Collection;
import java.util.Date;

public class UserResources {
    private static final UserResources SINGLETON = new UserResources();

    private UserResources() {
    }

    public static UserResources getSingleton() {
        return SINGLETON;
    }

    private static final CustomerService customerService = CustomerService.getSingleton();
    private static final ReservationService reservationService = ReservationService.getSingleton();

    public static void createUser(String fName, String lName, String email) {
        customerService.addCustomer(fName, lName, email);
    }

    public static void createReservation(final Customer customer, final IRoom room, Date checkInDate, Date checkOutDate) {
        reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

//    public static Collection<IRoom> displayAvailableRooms(Date checkInDate, Date checkOutDate) {
//        return reservationService.findRooms(checkInDate, checkOutDate);
//    }

    public static boolean isEmailInSystem(String email) {
        return customerService.isEmailInSystem(email);
    }

    public static Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static Collection<Reservation> getReservations(Customer customer) {
        return reservationService.getCustomersReservation(customer);
    }
}
