package userInterface;

import java.util.Scanner;

import static resources.customer.CustomerService.createUser;


public class CreateUserAccount {
    private static String fName;
    private static String lName;
    private static String email;
    private static String input;

    public static void createAccount() {
        boolean valid = true;
        Scanner scannerUserCreation = new Scanner(System.in);
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\t\t\t-Create an Account-");
//        System.out.println("Your email must be in the proper format \"joe@doe.com\":");
        System.out.println("Type \"back\" anytime to go back to the main menu");
        System.out.println("-------------------------------------------------------");
        do {
            do {
                if (!valid) {
                    System.out.println("\nOnly letters allowed\nCheck your input and try again\n");
                }

                System.out.print("Enter your First Name: ");
                input = scannerUserCreation.nextLine();
                fName = input;
                if(input.equals("back")){
                    break;
                }
                System.out.println(fName);
                valid = false;
            } while (!fName.matches("^[a-zA-Z]*$"));
            valid = true;
            if(input.equals("back")){
                break;
            }

            do {
                if (!valid) {
                    System.out.println("\nOnly letters allowed\nCheck your input and try again\n");
                }
                System.out.print("Enter your Last Name: ");
                input = scannerUserCreation.nextLine();
                lName = input;
                if(input.equals("back")){
                    break;
                }
                System.out.println(lName);
                valid = false;
            } while (!lName.matches("^[a-zA-Z]*$"));
            valid = true;
            if(input.equals("back")){
                break;
            }

            do {
                if (!valid) {
                    System.out.println("Your email must be in the proper format \"joe@doe.com\":");
                }
                System.out.print("Enter your Email: ");
                input = scannerUserCreation.nextLine();
                email = input;
                if(input.equals("back")){
                    break;
                }
                System.out.println(email);
                valid = false;
            } while (!email.matches("^(.+)@(.+).com$"));
            if(!input.equals("back")){
                createUser(fName, lName, email);
            }
            input = "back";
        } while (!input.equals("back"));
    }
}
