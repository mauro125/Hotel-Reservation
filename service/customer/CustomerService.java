package service.customer;

import model.customer.Customer;

import java.util.Collection;
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

    public static boolean isEmailInSystem(String email) {
        return customers.containsKey(email);
    }

    public static void addCustomer(String fName, String lName, String email) {
        Customer customer = new Customer(fName, lName, email);
        customers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
