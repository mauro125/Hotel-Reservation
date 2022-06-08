package userInterface.adminMenu;

import java.util.Scanner;

public class CreateRoom {
    public static void createRoom() {
        boolean valid = true;
        Scanner scannerRoomCreation = new Scanner(System.in);
        String input;
        do {
            int roomNumber = 0;
            do {
                if (!valid) {
                    System.out.println("Only numbers are allowed");
                }
                System.out.print("Enter Room Number: ");
                input = scannerRoomCreation.nextLine();
                if (input.equals("back")) {
                    break;
                }
                if (input.matches("^[0-9]*$")) {
                    roomNumber = Integer.parseInt(input);
                }
                valid = false;
            } while (!input.matches("^[0-9]*$"));
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
                if (input.matches("\\d+(\\.\\d+)?")) {
                    price = Integer.parseInt(input);
                }
                valid = false;
            } while (!input.matches("\\d+(\\.\\d+)?"));
            valid = true;
            if (input.equals("back")) {
                break;
            }
            System.out.println("Room Number: " + roomNumber + "\nPrice: " + price);
        } while (!valid);
    }
}
