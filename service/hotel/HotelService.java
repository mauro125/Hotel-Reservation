package service.hotel;

import model.customer.Customer;
import model.room.IRoom;
import model.room.Room;
import model.roomEnum.RoomType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HotelService {
    private static final HotelService SINGLETON = new HotelService();

    private HotelService() {
    }

    public static HotelService getSingleton() {
        return SINGLETON;
    }

    static Map<String, IRoom> rooms = new HashMap<>();
    static Map<String, IRoom> unavailableRooms = new HashMap<>();
    public static void addRoom(String roomNumber, Double price, RoomType roomType) {
        final Room room = new Room(roomNumber, price, roomType);
        rooms.put(roomNumber, room);
        unavailableRooms.put(roomNumber, room);
    }

    public static void removeRoom(String roomNumber) {
        unavailableRooms.remove(roomNumber);
        System.out.println(unavailableRooms.values());
    }

    public boolean isRoomInSystem(String roomNumber) {
        return rooms.containsKey(roomNumber);
    }

//    public Collection<IRoom> getRoom(String roomNumber) {
//        return (Collection<IRoom>) rooms.get(roomNumber);
//    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

//    public Collection<IRoom> getAllUnavailableRooms() {
//        return unavailableRooms.values();
//    }

    public static void displayAllRooms() {
        if (rooms.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\t\tNo Rooms found");
            System.out.println("********************************************************\n");
        } else {
            System.out.println("\n\t\t\t\tAll Rooms");
            System.out.println("------------------------------------------------------");
            System.out.println("Room Number\t\tRoom Price\t\tRoom Type");
            System.out.println("------------------------------------------------------");
            for (Map.Entry<String, IRoom> entry : rooms.entrySet()) {
                String formattedStr = String.format("#%s\t\t\t$%.2f\t\t%s", entry.getKey(), entry.getValue().getRoomPrice(), entry.getValue().getRoomType());
                System.out.println(formattedStr);
            }
        }
    }
}
