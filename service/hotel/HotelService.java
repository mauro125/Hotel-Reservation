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

    public static void addRoom(String roomNumber, Double price, RoomType roomType) {
        Room room = new Room(roomNumber, price, roomType);
        rooms.put(roomNumber, room);
        //display all rooms
        getAllRooms();
    }

    public static void getAllRooms() {
        if (rooms.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\t\tNo Rooms found");
            System.out.println("********************************************************\n");
        } else {
            System.out.println("\t\t\t\tall customers");
            System.out.println("------------------------------------------------------");
            System.out.println("Room Number\t\tRoom Price\t\tRoom Type");
            System.out.println("------------------------------------------------------");
            for (Map.Entry<String, Room> entry : rooms.entrySet()) {
                String formattedStr = String.format("#%s\t\t\t$%.2f\t\t%s", entry.getKey(), entry.getValue().getRoomPrice(), entry.getValue().getRoomType());
                System.out.println(formattedStr);
            }
        }
    }
}
