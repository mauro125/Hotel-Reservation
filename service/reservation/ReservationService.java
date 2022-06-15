package service.reservation;

import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;
import service.hotel.HotelService;

import java.util.*;

public class ReservationService {
    private static final ReservationService SINGLETON = new ReservationService();
    private static final HotelService hotelService = HotelService.getSingleton();

    private ReservationService() {
    }

    public static ReservationService getSingleton() {
        return SINGLETON;
    }

    private static final Map<String, Collection<IRoom>> rooms = new HashMap<>();
    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), (Collection<IRoom>) room);
    }

    public IRoom getARoom(final String roomNumber) {
        return (IRoom) rooms.get(roomNumber);
    }

//    public void loadDummyData() {
//        Customer customer1 = new Customer("Mauricio", "Joseneto", "narama125@gmail.com");
//        Customer customer2 = new Customer("jeff", "Santos", "hector@gmail.com");
//        Room room1 = new Room("101", 100.0, SINGLE);
//        Room room2 = new Room("102", 120.0, DOUBLE);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2031, 02, 01);
//        Date checkInDate = calendar.getTime();
//        calendar.set(2031, 02, 05);
//        Date checkOutDate = calendar.getTime();
//
//        Reservation reservation1 = new Reservation(customer1, room1, checkInDate, checkOutDate);
//        calendar.set(2031, 02, 02);
//        checkInDate = calendar.getTime();
//        calendar.set(2031, 02, 03);
//        checkOutDate = calendar.getTime();
//
//        Reservation reservation2 = new Reservation(customer2, room2, checkInDate, checkOutDate);
//
////        Collection<Reservation> customerReservations = getCustomersReservation(customer1);
//
//
////        Collection<Reservation> customerReservations = getCustomersReservation(customer);
//
//
////        customerReservations.add(reservation1);
////        reservations.put(customer1.getEmail(),  customerReservations);
////        customerReservations.add(reservation2);
////        reservations.put(customer1.getEmail(),  customerReservations);
//    }
//
//
//        return reservations.put(customer.getEmail(), reservation);
//    }

    public Reservation reserveARoom(final Customer customer, final IRoom room, final Date checkInDate, final Date checkOutDate) {
        final Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> reservationsInSystem = getCustomersReservation(customer);

        if (reservationsInSystem == null) {
            reservationsInSystem = new ArrayList<>();
        }
        reservationsInSystem.add(reservation);
        reservations.put(customer.getEmail(), reservationsInSystem);
        return reservation;
    }

    public Collection<IRoom> findRoomsDefault(Date checkInDate, Date checkOutDate) {
        return findRooms(checkInDate, checkOutDate);
    }

    Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<Reservation> reservations = getAllReservations();
        Collection<IRoom> availableRooms = hotelService.getAllRooms();
        if (reservations.size() == 0) {
            return availableRooms;
        }
        for (Reservation reservation : reservations) {
            String unAvailableRoomNumba = reservation.getRoom().getRoomNumber();
            if ((reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate))) {
                availableRooms = availableRooms.stream().filter(room -> !room.getRoomNumber().equals(unAvailableRoomNumba)).toList();
            } else {
                availableRooms = availableRooms.stream().filter(room -> room.getRoomNumber().equals(unAvailableRoomNumba)).toList();
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getAllReservations() {
        final Collection<Reservation> reservationsInSystem = new ArrayList<>();

        for (Collection<Reservation> reservations : reservations.values()) {
            reservationsInSystem.addAll((Collection<? extends Reservation>) reservations);
        }
        return reservationsInSystem;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return (Collection<Reservation>) reservations.get(customer.getEmail());
    }

}
