package userInterface.createReservation;

import model.customer.Customer;
import model.room.IRoom;
import model.room.Room;
import org.jetbrains.annotations.NotNull;
import resources.UserResources;

import java.text.SimpleDateFormat;
import java.util.*;

import static utils.PrintStuff.displayAvailableRooms;

public class BookARoom {
    //    private static final ReservationService reservationService = ReservationService.getSingleton();
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
        String hasRooms;
        boolean futureRooms;

//        reservationService.loadDummyData();


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
                    System.out.println("\t\tDate must be in the format MM/DD/YYYY");
                    System.out.println("\t\t\tCheck your input and try again");
                    System.out.println("-------------------------------------------------------\n");
                }

                System.out.print("Enter Check-In Date (MM/DD/YYYY): ");
                input = scannerRoomCreation.nextLine();
                if (input.equals("back")) {
                    break;
                }
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
                        System.out.println("\t\t\tDate must be in the format MM/DD/YYYY");
                        System.out.println("\t\t\t\tCheck your input and try again");
                        System.out.println("-------------------------------------------------------\n");
                    } else if (checkOutIsBeforeCheckin) {
                        checkOutIsBeforeCheckin = false;
                        System.out.println("\n-------------------------------------------------------");
                        System.out.println("\tCheck-out Date must be after Check-in Date");
                        System.out.println("\t\tCheck your input and try again");
                        System.out.println("-------------------------------------------------------\n");
                    }
                }

                System.out.print("Enter Check-Out Date (MM/DD/YYYY): ");
                input = scannerRoomCreation.nextLine();
                if (input.equals("back")) {
                    break;
                }
                if (input.matches(regex)) {
                    checkOutDate = getDate(input);
                    if (Objects.requireNonNull(checkInDate).after(checkOutDate)) {
                        checkOutIsBeforeCheckin = true;
                    }
                } else {
                    valid = false;
                }
            } while (!input.matches(regex) || checkOutIsBeforeCheckin);
            if (input.equals("back")) {
                break;
            }
            System.out.println("\n-------------------------------------------------------");
            System.out.println("\t\t\tSearching for available rooms...");
            System.out.println("-------------------------------------------------------\n\n");

            Collection<IRoom> availableRooms = userResources.displayAvailableRooms(checkInDate, checkOutDate);

            Date futureCheckIn = futureDate(checkInDate);
            Date futureCheckOut = futureDate(checkOutDate);

            Collection<IRoom> availableFutureRooms = userResources.displayAvailableRooms(futureCheckIn, futureCheckOut);

            do {
                hasRooms = displayAvailableRooms(availableRooms, availableFutureRooms);
                if (hasRooms.equals("no rooms")) {
                    break;
                } else {
                    System.out.print("Do you have an account? (y/n): ");
                    input = scannerRoomCreation.nextLine();
                    if (input.equals("back")) {
                        break;
                    }
                    if (input.equals("y")) {
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
                                    System.out.println("\t\t\t\temail not found");
                                    System.out.println("type \"back\" to go back to the main menu or try again");
                                    System.out.println("-------------------------------------------------------\n");
                                }
                            }
                            System.out.print("Enter your email address: ");
                            input = scannerRoomCreation.nextLine();
                            email = input;
                            if (email.equals("back")) {
                                break;
                            }
                            if (!email.matches("^(.+)@(.+).com$")) {
                                valid = false;
                            } else {
                                if (userResources.isEmailInSystem(email)) {
                                    customer = userResources.getCustomer(email);
                                    emailFound = true;
                                    valid = true;
                                } else {
                                    emailFound = false;
                                }
                            }
                        } while (!email.matches("^(.+)@(.+).com$") || !emailFound);
                        if (email.equals("back")) {
                            break;
                        }
                        valid = true;
                    } else if (input.equals("n")) {
                        System.out.println("\n-------------------------------------------------------");
                        System.out.println("\tAn account is required to book a room");
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
                }
                if (input.equals("back")) {
                    break;
                }

            } while (!valid && !input.equals("back"));

            if (hasRooms.equals("no rooms")) {
                break;
            }
            if (input.equals("back")) {
                break;
            }
            do {
                System.out.print("What room would you like to book: ");
                input = scannerRoomCreation.nextLine();
                if (input.equals("back")) {
                    break;
                }
                String finalInput = input;

                if (hasRooms.equals("current rooms")) {
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
                            System.out.println("\t\t\t-That room is not available to be booked-");
                            System.out.println("\tType \"back\" anytime to go back to the main menu");
                            System.out.println("-------------------------------------------------------");
                            valid = false;
                        }
                    }
                } else if (hasRooms.equals("future rooms")) {
                    int count = 0;
                    for (IRoom room : availableFutureRooms) {
                        count++;
                        if (room.getRoomNumber().equals(finalInput)) {
                            roomToBook = (Room) room;
                            valid = true;
                            break;
                        }
                        if (count == availableFutureRooms.size()) {
                            System.out.println("\n-------------------------------------------------------");
                            System.out.println("\t\t\t-That room is not available to be booked-");
                            System.out.println("\tType \"back\" anytime to go back to the main menu");
                            System.out.println("-------------------------------------------------------");
                            valid = false;
                        }
                    }
                }
                System.out.println(roomToBook);
//                }
            } while (!valid);
            if (input.equals("back")) {
                break;
            }

            if (hasRooms.equals("current rooms")) {
                System.out.println("\n-------------------------------------------------------");
                System.out.println("\t\t\tBooking Details\n");
                assert customer != null;
                System.out.println("Customer: " + customer);
                assert roomToBook != null;
                System.out.println("Room: " + roomToBook);
                System.out.println("Check-in: " + new SimpleDateFormat("MM/dd/yyyy").format(checkInDate));
                System.out.println("Check-out: " + new SimpleDateFormat("MM/dd/yyyy").format(checkOutDate));
                System.out.println("-------------------------------------------------------");
            } else if (hasRooms.equals("future rooms")) {
                System.out.println("\n-------------------------------------------------------");
                System.out.println("\t\t\tBooking Details for 7 days more from original search\n");
                assert customer != null;
                System.out.println("Customer: " + customer);
                assert roomToBook != null;
                System.out.println("Room: " + roomToBook);
                System.out.println("Check-in: " + new SimpleDateFormat("MM/dd/yyyy").format(futureCheckIn));
                System.out.println("Check-out: " + new SimpleDateFormat("MM/dd/yyyy").format(futureCheckOut));
                System.out.println("-------------------------------------------------------");
            }
            do {
                System.out.print("\nConfirm booking? (y/n): ");
                input = scannerRoomCreation.nextLine().toLowerCase();
                if (input.equals("back")) {
                    break;
                }
                if (input.equals("y")) {
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("\t\t\t\tBooking confirmed!");
                    System.out.println("-------------------------------------------------------\n\n");
                    if (hasRooms.equals("current rooms")) {
                        userResources.createReservation(customer, roomToBook, checkInDate, checkOutDate);
                    } else if (hasRooms.equals("future rooms")) {
                        userResources.createReservation(customer, roomToBook, futureCheckIn, futureCheckOut);

                    }
//                    availableRooms.clear();
//                    availableFutureRooms.clear();
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
        }
        while (!valid);
    }

    @NotNull
    private static Date getDate(String input) {
        Date inputDate;
        Calendar calendar = Calendar.getInstance();
        int month = Integer.parseInt(input.substring(0, 2));
        int day = Integer.parseInt(input.substring(3, 5));
        int year = Integer.parseInt(input.substring(6, 10));
        calendar.set(year, month - 1, day);
        inputDate = calendar.getTime();
        return inputDate;
    }

    static Date futureDate(Date date) {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.add(Calendar.DATE, 7);
        return calendar2.getTime();
    }
}
