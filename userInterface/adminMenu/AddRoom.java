package userInterface.adminMenu;

import resources.AdminResource;
import model.roomEnum.RoomType;

import java.util.Scanner;

public class AddRoom {
    private static final AdminResource adminResource = AdminResource.getSingleton();

    public static void addRoom() {
        boolean valid = true;
        boolean addAnotherRoom = true;
        Scanner scannerRoomCreation = new Scanner(System.in);
        String input;


        do {
            System.out.println("\n\n-------------------------------------------------------");
            System.out.println("\t\t\t\t\t-Add a Room-");
            System.out.println("\tType \"back\" anytime to go back to the main menu");
            System.out.println("-------------------------------------------------------");
            String roomNumber = "";
            do {
                if (!valid) {
                    System.out.println("check your input and try again");
                }
                System.out.print("Enter Room Number: ");
                input = scannerRoomCreation.nextLine();
                roomNumber = input;
                if (input.equals("back")) {
                    break;
                }
                valid = false;
            } while (input.equals(""));
            valid = true;
            if (input.equals("back")) {
                break;
            }

            double price = 0;
            do {
                if (!valid) {
                    System.out.println("Check your input and try again");
                }
                System.out.print("Enter Price: ");
                input = scannerRoomCreation.nextLine();
                if (input.equals("back")) {
                    break;
                }
                if (input.matches("^[0-9]*$") || input.matches("^[0-9]*\\.[0-9]*$")) {
                    price = Double.parseDouble(input);
                    System.out.printf("%.2f", price);
                    System.out.println("\n");
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
                    System.out.println("Check your input and try again");
                }
                System.out.println("Options for room type: write 1 or single, 2 or double");
                System.out.print("Enter Room Type: ");
                input = scannerRoomCreation.nextLine().toLowerCase();
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
                adminResource.addRoom(roomNumber, price, roomType);

                while (!(input.equals("y") || input.equals("n"))) {
                    System.out.print("\nWould you like to add another room? Y/N: ");
                    input = String.valueOf(scannerRoomCreation.nextLine().toLowerCase().charAt(0));
                    if (input.equals("y")) {
                        addAnotherRoom = true;
                    } else if (input.equals("n")) {
                        addAnotherRoom = false;
                        break;
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            }
        } while (addAnotherRoom);
    }
}
