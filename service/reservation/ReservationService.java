package service.reservation;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import model.room.Room;
import service.hotel.HotelService;

import java.util.*;
import java.util.stream.Collectors;

import static model.roomEnum.RoomType.DOUBLE;
import static model.roomEnum.RoomType.SINGLE;

public class ReservationService {
    private static final ReservationService SINGLETON = new ReservationService();
    private static final HotelService hotelService = HotelService.getSingleton();

    private ReservationService() {
    }

    public static ReservationService getSingleton() {
        return SINGLETON;
    }

    private final Map<String, Collection<IRoom>> rooms = new HashMap<>();
    //    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();
    static Map<String, Reservation> reservations = new HashMap<>();

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), (Collection<IRoom>) room);
    }

    public IRoom getARoom(final String roomNumber) {
        return (IRoom) rooms.get(roomNumber);
    }

    public void loadDummyData() {
        Customer customer1 = new Customer("Mauricio", "Joseneto", "narama125@gmail.com");
        Customer customer2 = new Customer("jeff", "Santos", "hector@gmail.com");
        Room room1 = new Room("101", 100.0, SINGLE);
        Room room2 = new Room("102", 120.0, DOUBLE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2031, 02, 01);
        Date checkInDate = calendar.getTime();
        calendar.set(2031, 02, 05);
        Date checkOutDate = calendar.getTime();

        Reservation reservation1 = new Reservation(customer1, room1, checkInDate, checkOutDate);
        calendar.set(2031, 02, 02);
        checkInDate = calendar.getTime();
        calendar.set(2031, 02, 03);
        checkOutDate = calendar.getTime();

        Reservation reservation2 = new Reservation(customer2, room2, checkInDate, checkOutDate);

        reservations.put(customer1.getEmail(), reservation1);
        reservations.put(customer2.getEmail(), reservation2);
    }


    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        final Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
//        Reservation reservation = new Reservation(getCustomer("narama125@mail.com"), (IRoom) hotelService.getRoom("101"), checkInDate, checkOutDate);


        return reservations.put(customer.getEmail(), reservation);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Map<String, Collection<IRoom>> uniqueOpenRooms = new HashMap<>();
        Set<String> openRooms = new HashSet<>();

        Collection<Reservation> reservations = getAllReservations();
//        reservations = getAllReservations();

        Collection<IRoom> rooms = hotelService.getAllRooms();
        if (reservations.size() == 0) {
            return rooms;
        }
        for (Reservation reservation : reservations) {
            if (!(reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate))) {
                openRooms.add(reservation.getRoom().getRoomNumber());
            }
        }

//        for (IRoom ignored : rooms) {
//            openRooms.addAll(rooms.stream().map(IRoom::getRoomNumber).toList());
//        }
        openRooms.addAll(rooms.stream().map(IRoom::getRoomNumber).toList());
        Iterator<String> openRoomsIterator = openRooms.iterator();
        Collection<IRoom> roomCollection = null;
        while (openRoomsIterator.hasNext()) {
            String roomNumber = String.valueOf(openRoomsIterator.next());
            System.out.println(roomNumber);
//            System.out.println(openRoomsIterator.next());
//            roomCollection = hotelService.getRoom(roomNumber);
//            uniqu
//            uniqueOpenRooms.put(roomNumber, hotelService.getRoom(roomNumber));
            roomCollection = rooms.stream().filter(room -> room.getRoomNumber().equals(roomNumber)).toList();
            uniqueOpenRooms.put(roomNumber, roomCollection);
        }
//        openRooms.addAll(openRooms);
//        return rooms.stream().filter(room -> !openRooms.contains(room.getRoomNumber())).collect(Collectors.toList());
        return uniqueOpenRooms.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
//        for (Collection<IRoom> reservation : reservations.values()) {
//            if (reservations.getCheckOutDate()(cehckInDate) && reservations.after(checkOutDate)) {
//                return room;
//            }
//        }

//        return ;
    }

//    private Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {
//        final Collection<Reservation> allReservations = getAllReservations();
//        Collection<Reservation> availableRooms = getAllReservations();
//        return checkInDate.before(availableRooms.getCheckOutDate())
//                && checkOutDate.after(availableRooms.getCheckInDate());
//
//        return availableRooms;
//    }

    public Collection<Reservation> getAllReservations() {
        return reservations.values();
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return (Collection<Reservation>) reservations.get(customer.getEmail());
    }

    public void printAllReservations() {
        for (Reservation reservation : getAllReservations()) {
            System.out.println(reservation);
        }
    }


}
