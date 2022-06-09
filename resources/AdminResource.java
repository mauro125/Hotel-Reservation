package resources;

import model.customer.Customer;
import roomEnum.RoomType;
import service.customer.CustomerService;
import service.hotel.HotelService;

public class AdminResource {

    private static final AdminResource SINGLETON = new AdminResource();

    private AdminResource() {
    }

    public static AdminResource getSingleton() {
        return SINGLETON;
    }

    private static final CustomerService customerService = CustomerService.getSingleton();
    private static final HotelService hotelService = HotelService.getSingleton();

    public static void getAllCustomers() {
        customerService.getAllCustomers();
    }

    public static void addRoom(int roomNumber, Double price, RoomType roomType) {
        hotelService.addRoom(roomNumber, price, roomType);
    }

    public static void displayAllRooms() {
        hotelService.getAllRooms();
    }
}
