package resources.customer;
import model.customer.Customer;

public class CustomerService {
    public static void createUser(String fName, String lName, String email) {
//        System.out.println("Customer " + fName + " " + lName + " " + email + " has been created");
        Customer customer = new Customer(fName, lName, email);



        //add customer to list using map or something else
    }
}
