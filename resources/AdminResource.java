package resources;

import model.customer.Customer;
import service.customer.CustomerService;

public class AdminResource {

    private static final AdminResource SINGLETON = new AdminResource();

    private AdminResource() {
    }

    public static AdminResource getSingleton() {
        return SINGLETON;
    }

    private static final CustomerService customerService = CustomerService.getSingleton();

    public static void getAllCustomers() {
        customerService.getAllCustomers();
    }

    public static void createUser(String fName, String lName, String email) {
        customerService.createUser(fName, lName, email);
    }
}
