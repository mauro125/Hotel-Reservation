package userInterface.adminMenu;

import model.roomEnum.RoomType;
import resources.AdminResources;

import java.util.Scanner;

public class AddRoom {
    private static final AdminResources adminResources = AdminResources.getSingleton();

    public static void addRoom() {
        boolean valid = true;
        boolean addAnotherRoom = true;
        Scanner scannerRoomCreation = new Scanner(System.in);
        String input;


        System.out.println("\n-------------------------------------------------------");
        System.out.println("\t\t\t\t\t-Add a Room-");
        System.out.println("\tType \"back\" anytime to go back to the main menu");
        System.out.println("-------------------------------------------------------");
        String roomNumber = "";
        do {
            if (!valid) {
                System.out.println("\n\n-------------------------------------------------------");
                System.out.println("\t\t\t\tcheck your input and try again");
                System.out.println("\tType \"back\" anytime to go back to the main menu");
                System.out.println("-------------------------------------------------------\n");
            }
            System.out.print("Enter Room Number: ");
            input = scannerRoomCreation.next();
            roomNumber = input;
            if (input.equals("back")) {
                break;
            }
            if (adminResources.isRoomInSystem(roomNumber)) {
                do {
                    if (!valid) {
                        System.out.println("\n\n-------------------------------------------------------");
                        System.out.println("\t\t\t\tcheck your input and try again");
                        System.out.println("\tType \"back\" anytime to go back to the main menu");
                        System.out.println("-------------------------------------------------------\n");
                    }

                    System.out.println("\nRoom already exists");
                    System.out.println("If you continue, old values will be lost");
                    System.out.print("Continue? (y/n): ");
                    input = scannerRoomCreation.next().toLowerCase();
                    if (input.equals("back")) {
                        break;
                    }

                    if (input.equals("y")) {
                        valid = true;
                        input = "";
                        break;
                    } else if (input.equals("n")) {
                        valid = true;
                        input = "back";
                        break;
                    } else {
                        valid = false;
                    }
                } while (!valid);
            }
            valid = true;
            if (input.equals("back")) {
                break;
            }

            double price = 0;
            do {
                if (!valid) {
                    System.out.println("\n\n-------------------------------------------------------");
                    System.out.println("\t\t\t\tcheck your input and try again");
                    System.out.println("\tType \"back\" anytime to go back to the main menu");
                    System.out.println("-------------------------------------------------------\n");
                }
                System.out.print("Enter Price: ");
                input = scannerRoomCreation.next();
                if (input.equals("back")) {
                    break;
                }

                if (input.matches("^[0-9]*$") || input.matches("^[0-9]*\\.[0-9]*$")) {
                    price = Double.parseDouble(input);
                }
                valid = false;
            } while (!input.matches("\\d+(\\.\\d+)?"));
            valid = true;
            if (input.equals("back")) {
                break;
            }

            RoomType roomType = null;
            do {
                if (!valid) {
                    System.out.println("\n\n-------------------------------------------------------");
                    System.out.println("\t\t\tcheck your input and try again");
                    System.out.println("\tType \"back\" anytime to go back to the main menu");
                    System.out.println("-------------------------------------------------------\n");
                }
                System.out.println("Options for room type: write 1 or single, 2 or double");
                System.out.print("Enter Room Type: ");
                input = scannerRoomCreation.next().toLowerCase();
                if (input.equals("back")) {
                    break;
                }
                if (input.equals("single") || input.equals("1")) {
                    roomType = RoomType.SINGLE;
                } else if (input.equals("double") || input.equals("2")) {
                    roomType = RoomType.DOUBLE;
                }
                valid = false;
            } while (!(input.equals("1") || input.equals("2") || input.equals("single") || input.equals("double")));
            valid = true;
            if (input.equals("back")) {
                break;
            }

            if (!input.equals("back")) {
                adminResources.addRoom(roomNumber, price, roomType);
                System.out.println("\n\n-------------------------------------------------------");
                System.out.println("\t\t\t\tRoom #" + roomNumber + " added");
                System.out.println("-------------------------------------------------------\n");

                while (!(input.equals("y") || input.equals("n"))) {
                    System.out.print("\nWould you like to add another room? (y/n): ");
                    input = String.valueOf(scannerRoomCreation.next().toLowerCase().charAt(0));
                    if (input.equals("y")) {
                        addAnotherRoom = true;
                    } else if (input.equals("n")) {
                        addAnotherRoom = false;
                        break;
                    } else {
                        System.out.println("\n\n-------------------------------------------------------");
                        System.out.println("\t\t\tInvalid input");
                        System.out.println("type \"back\" to go back to the main menu or try again");
                        System.out.println("-------------------------------------------------------\n\n");
                    }
                }
            }
        } while (addAnotherRoom);
    }
}
