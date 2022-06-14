package resources;

import model.customer.Customer;
import model.reservation.Reservation;
import model.roomEnum.RoomType;
import service.customer.CustomerService;
import service.hotel.HotelService;
import service.reservation.ReservationService;

import java.util.Collection;

public class AdminResources {

    private static final AdminResources SINGLETON = new AdminResources();

    private AdminResources() {
    }

    public static AdminResources getSingleton() {
        return SINGLETON;
    }

    private static final CustomerService customerService = CustomerService.getSingleton();
    private static final HotelService hotelService = HotelService.getSingleton();
    private static final ReservationService reservationService = ReservationService.getSingleton();

    public static Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public static Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static void addRoom(String roomNumber, Double price, RoomType roomType) {
        hotelService.addRoom(roomNumber, price, roomType);
    }

    public static void displayAllRooms() {
        hotelService.displayAllRooms();
    }

    public static Collection<Reservation> displayAllReservations() {
        return reservationService.getAllReservations();
    }

    public static boolean isRoomInSystem(String roomNumber) {
        return hotelService.isRoomInSystem(roomNumber);
    }
}
