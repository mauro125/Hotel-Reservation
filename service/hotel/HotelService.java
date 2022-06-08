package service.hotel;

import model.room.Room;
import roomEnum.RoomType;

import java.util.HashMap;
import java.util.Map;

public class HotelService {
    private static final HotelService SINGLETON = new HotelService();

    private HotelService() {
    }

    public static HotelService getSingleton() {
        return SINGLETON;
    }

    static Map<String, Room> rooms = new HashMap<>();

    public static void addRoom(int roomNumber, Double price, RoomType roomType) {
        Room room = new Room(roomNumber, price, roomType);
    }

    public static void getAllCustomers() {
        if (rooms.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\tNo Rooms found");
            System.out.println("********************************************************\n");
        } else {
            System.out.println("\t\t\t\tall customers");
            System.out.println("------------------------------------------------------");
            System.out.println("Room Number\t\tRoom Price\t\tRoom Type");
            System.out.println("------------------------------------------------------");
            for (Map.Entry<String, Room> entry : rooms.entrySet()) {
                System.out.println(entry.getValue().getRoomNumber() + "\t\t\t" + entry.getValue().getRoomPrice() +
                        "\t\t\t" + entry.getValue().getRoomType());
            }
        }
    }
}
