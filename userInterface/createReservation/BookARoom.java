package userInterface.createReservation;

import org.jetbrains.annotations.NotNull;
import service.hotel.HotelService;
import service.reservation.ReservationService;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import static utils.PrintStuff.displayAvailableRooms;

public class BookARoom {
    private static final ReservationService reservationService = ReservationService.getSingleton();
    public static void bookARoom() {
        String input = "";
        boolean valid = true;
        Date checkInDate = null;
        Date checkOutDate = null;
        String email = "";
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Scanner scannerRoomCreation = new Scanner(System.in);
        boolean checkOutIsBeforeCheckin = false;
        reservationService.loadDummyData();
        do {
            System.out.println("\n\n-------------------------------------------------------");
            System.out.println("\t\t\t\t\t-Reserve a Room-");
            System.out.println("\tType \"back\" anytime to go back to the main menu");
            System.out.println("-------------------------------------------------------");
            do {
                if (input.equals("back")) {
                    break;
                }
                if (!valid) {
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("Date must be in the format MM/DD/YYYY");
                    System.out.println("Check your input and try again");
                    System.out.println("-------------------------------------------------------\n");
                }

                System.out.print("Enter Check-In Date (MM/DD/YYYY): ");
                input = scannerRoomCreation.nextLine();

                if (input.matches(regex)) {
                    checkInDate = getDate(input);
                }
                valid = false;
            } while (!input.matches(regex));
            valid = true;
            if (input.equals("back")) {
                break;
            }

            do {
                if (input.equals("back")) {
                    break;
                }
                if (!valid || checkOutIsBeforeCheckin) {
                    if (!valid) {
                        valid = true;
                        System.out.println("\n-------------------------------------------------------");
                        System.out.println("Date must be in the format MM/DD/YYYY");
                        System.out.println("Check your input and try again");
                        System.out.println("-------------------------------------------------------\n");
                    } else if (checkOutIsBeforeCheckin) {
                        checkOutIsBeforeCheckin = false;
                        System.out.println("\n-------------------------------------------------------");
                        System.out.println("Check-out Date must be after Check-in Date");
                        System.out.println("Check your input and try again");
                        System.out.println("-------------------------------------------------------\n");
                    }
                }

                System.out.print("Enter Check-Out Date (MM/DD/YYYY): ");
                input = scannerRoomCreation.nextLine();

                if (input.matches(regex)) {
                    checkOutDate = getDate(input);
                    if (Objects.requireNonNull(checkInDate).after(checkOutDate)) {
                        checkOutIsBeforeCheckin = true;
                    }
                } else {
                    valid = false;
                }
            } while (!input.matches(regex) || checkOutIsBeforeCheckin);
            System.out.println("-------------------------------------------------------");
            System.out.println("Searching for available rooms...");
            System.out.println("-------------------------------------------------------\n\n");
            displayAvailableRooms(reservationService.findRooms(checkInDate, checkOutDate));
        } while (!valid);
    }

    @NotNull
    private static Date getDate(String input) {
        Date inputDate;
        Calendar calendar = Calendar.getInstance();
        int month = Integer.parseInt(input.substring(0, 2));
        int day = Integer.parseInt(input.substring(3, 5));
        int year = Integer.parseInt(input.substring(6, 10));
        calendar.set(year-1, month, day);
        inputDate = calendar.getTime();
        System.out.println(inputDate);
        return inputDate;
    }
}
