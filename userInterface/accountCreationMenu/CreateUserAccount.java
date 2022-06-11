package userInterface.accountCreationMenu;

import resources.UserResources;
import java.util.Scanner;

public class CreateUserAccount {
    private static final UserResources userResources = UserResources.getSingleton();

    public static void createAccount() {
        boolean valid = true;
        Scanner scannerUserCreation = new Scanner(System.in);
        System.out.println("\n\n-------------------------------------------------------");
        System.out.println("\t\t\t\t-Create an Account-");
        System.out.println("Type \"back\" anytime to go back to the main menu");
        System.out.println("-------------------------------------------------------");
        String input;
        do {
            String email;
            boolean emailInUse = false;
            do {
                if (!valid || emailInUse) {
                    if(!valid) {
                        System.out.println("Your email must be in the proper format \"joe@doe.com\":");
                    }
                    if (emailInUse) {
                        System.out.println("That email is already in use");
                        System.out.println("Please enter a different email");
                    }
                }
                System.out.print("Enter your Email: ");
                input = scannerUserCreation.nextLine().toLowerCase();
                email = input;

                if (input.equals("back")) {
                    break;
                }
                if (!email.matches("^(.+)@(.+).com$")) {
                    valid = false;
                }
                if(userResources.isEmailInSystem(email)) {
                    emailInUse = true;
                    valid = true;
                    email = "email already in use";
                }
            } while (!email.matches("^(.+)@(.+).com$"));
            valid = true;
            if (input.equals("back")) {
                break;
            }

            String fName;
            do {
                if (!valid) {
                    System.out.println("\nOnly letters allowed\nCheck your input and try again\n");
                }
                System.out.print("Enter your First Name: ");
                input = scannerUserCreation.nextLine();
                fName = input.substring(0, 1).toUpperCase() + input.substring(1);
                if (input.equals("back")) {
                    break;
                }
                System.out.println(fName);
                valid = false;
            } while (!fName.matches("^[a-zA-Z]*$"));
            valid = true;
            if (input.equals("back")) {
                break;
            }

            String lName;
            do {
                if (!valid) {
                    System.out.println("\nOnly letters allowed\nCheck your input and try again\n");
                }
                System.out.print("Enter your Last Name: ");
                input = scannerUserCreation.nextLine();
                lName = input.substring(0, 1).toUpperCase() + input.substring(1);
                if (input.equals("back")) {
                    break;
                }
                System.out.println(lName);
                valid = false;
            } while (!lName.matches("^[a-zA-Z]*$"));

            if (!input.equals("back")) {
                userResources.createUser(fName, lName, email);
                break;
            }
        } while (!input.equals("back"));
    }
}
