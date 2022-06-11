package userInterface.createReservation;

import model.customer.Customer;
import model.room.IRoom;
import model.room.Room;
import org.jetbrains.annotations.NotNull;
import resources.UserResources;
import service.reservation.ReservationService;

import java.text.SimpleDateFormat;
import java.util.*;

import static utils.PrintStuff.displayAvailableRooms;

public class BookARoom {
    private static final ReservationService reservationService = ReservationService.getSingleton();
    private static final UserResources userResources = UserResources.getSingleton();

    public static void bookARoom() {
        String input = "";
        boolean valid = true;
        Date checkInDate = null;
        Date checkOutDate = null;
        String email = "";
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Scanner scannerRoomCreation = new Scanner(System.in);
        boolean checkOutIsBeforeCheckin = false;
        Customer customer = null;
        Room roomToBook = null;


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
            System.out.println("\n-------------------------------------------------------");
            System.out.println("Searching for available rooms...");
            System.out.println("-------------------------------------------------------\n\n");

            Collection<IRoom> availableRooms = reservationService.findRooms(checkInDate, checkOutDate);

//            displayAvailableRooms(reservationService.findRooms(checkInDate, checkOutDate));

            do {
                displayAvailableRooms(availableRooms);
                System.out.print("Do you have an account? (y/n): ");
                input = scannerRoomCreation.nextLine();
                if (input.equals("y")) {
                    System.out.println("-------------------------------------------------------");
                    boolean emailFound = true;
                    do {
                        if (!valid || !emailFound) {
                            if (!valid) {
                                valid = true;
                                System.out.println("\n-------------------------------------------------------");
                                System.out.println("Your email must be in the proper format \"joe@doe.com\":");
                                System.out.println("type \"back\" to go back to the main menu or try again");
                                System.out.println("-------------------------------------------------------\n");
                            }
                            if (!emailFound) {
                                emailFound = true;
                                System.out.println("\n-------------------------------------------------------");
                                System.out.println("email not found");
                                System.out.println("type \"back\" to go back to the main menu or try again");
                                System.out.println("-------------------------------------------------------\n");
                            }
                        }
                        System.out.print("Enter your email address: ");
                        email = scannerRoomCreation.nextLine();
                        if (email.equals("back")) {
                            break;
                        }
                        if (!email.matches("^(.+)@(.+).com$")) {
                            valid = false;
                        } else {
                            if (userResources.isEmailInSystem(email)) {
                                customer = userResources.getCustomer(email);
                                System.out.println(customer.toString());
                                emailFound = true;
                                valid = true;
                                System.out.println("Account found!");
                            } else {
                                emailFound = false;
                            }
                        }
                    }
                    while (!email.matches("^(.+)@(.+).com$") || !emailFound);

                    valid = true;
                    System.out.println("-------------------------------------------------------\n\n");
                } else if (input.equals("n")) {
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("\tYou need to create an account to book a room");
                    System.out.println("\tSending back to Main menu...");
                    System.out.println("-------------------------------------------------------");
                    input = "back";
                    break;
                } else {
                    valid = false;
                    System.out.println("\n\n-------------------------------------------------------");
                    System.out.println("\t\t\tInvalid input");
                    System.out.println("-------------------------------------------------------\n\n");
                }
//                contains = availableRooms.con
//                for (IRoom room : availableRooms) {
//                    System.out.println(room.getRoomNumber());
//                }
            } while (!valid && !input.equals("back"));

            do {
//                if (!valid) {
//                    System.out.println("\n-------------------------------------------------------");
//                    System.out.println("\t\t\t-Check room number-");
//                    System.out.println("\tType \"back\" anytime to go back to the main menu");
//                    System.out.println("-------------------------------------------------------");
//                }
////                valid = false;
                System.out.print("What room would you like to book: ");
                input = scannerRoomCreation.nextLine();
                if (input.equals("back")) {
                    break;
                }

//                if (input.matches("^[0-9]+$")) {
//                    int roomNumber = Integer.parseInt(input);
                String finalInput = input;

                int count = 0;
                for (IRoom room : availableRooms) {
                    count++;
                    if (room.getRoomNumber().equals(finalInput)) {
                        roomToBook = (Room) room;
                        valid = true;
                        break;
                    }
                    if (count == availableRooms.size()) {
                        System.out.println("\n-------------------------------------------------------");
                        System.out.println("\t\t\t-Invalid input check room number-");
                        System.out.println("\tType \"back\" anytime to go back to the main menu");
                        System.out.println("-------------------------------------------------------");
                        valid = false;
                    }
                }
//                    if (availableRooms.stream().anyMatch(room -> room.getRoomNumber() == finalInput)) {
//                        roomToBook = (Room) availableRooms.stream().filter(room -> room.getRoomNumber() == finalInput).findFirst().get();
//                    }
                System.out.println(roomToBook);
//                }
            } while (!valid);

            Date formattedCheckIn = new Date();
            Date formattedCheckOut = new Date();


            System.out.println("\n-------------------------------------------------------");
            System.out.println("Booking Details\n");
            assert customer != null;
            System.out.println("Customer: " + customer.toString());
            assert roomToBook != null;
            System.out.println("Room: " + roomToBook.toString());
            System.out.println("Check-in: " + new SimpleDateFormat("MM/dd/yyyy").format(checkInDate));
            System.out.println("Check-out: " + new SimpleDateFormat("MM/dd/yyyy").format(checkOutDate));

            do {
                System.out.print("\nConfirm booking? (y/n): ");
                input = scannerRoomCreation.nextLine();
                if (input.equals("back")) {
                    break;
                }
                if (input.equals("y")) {
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("\t\t\t\tBooking confirmed!");
                    System.out.println("-------------------------------------------------------\n\n");
                    reservationService.reserveARoom(customer, roomToBook, checkInDate, checkOutDate);
                    valid = true;
                    break;
                } else if (input.equals("n")) {
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("\t\t\tBooking cancelled!");
                    System.out.println("-------------------------------------------------------\n\n");
                    valid = true;
                    break;
                } else {
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("\t\t\tInvalid input");
                    System.out.println("-------------------------------------------------------\n");
                    valid = false;
                }

            } while (!valid);

        } while (!valid);
    }

    @NotNull
    private static Date getDate(String input) {
        Date inputDate;
        Calendar calendar = Calendar.getInstance();
        int month = Integer.parseInt(input.substring(0, 2));
        int day = Integer.parseInt(input.substring(3, 5));
        int year = Integer.parseInt(input.substring(6, 10));
        calendar.set(year - 1, month, day);
        inputDate = calendar.getTime();
        System.out.println(inputDate);
        return inputDate;
    }
}
