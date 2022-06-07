package service.customer;

import model.customer.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static final CustomerService SINGLETON = new CustomerService();

    private CustomerService() {
    }

    public static CustomerService getSingleton() {
        return SINGLETON;
    }


    static Map<String, Customer> customers = new HashMap<>();

    public static void createUser(String fName, String lName, String email) {
        Customer customer = new Customer(fName, lName, email);
        customers.put(email, customer);
//
//        System.out.println("mapping customers");
//        for (Map.Entry<String, Customer> entry : customers.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
    }

    public static void getAllCustomers() {
        if (customers.size() == 0) {
            System.out.println("********************************************************");
            System.out.println("\t\t\t\tNo customers found");
            System.out.println("********************************************************\n");
        } else {
            System.out.println("\t\t\t\tall customers");
            System.out.println("------------------------------------------------------");
            System.out.println("First Name\t\tLast Name\t\tEmail");
            System.out.println("------------------------------------------------------");
            for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                System.out.println(entry.getValue().getfName() + "\t\t\t" + entry.getValue().getlName() +
                        "\t\t\t" + entry.getValue().getEmail());
            }
        }
    }
}
