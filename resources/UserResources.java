package resources;

import service.customer.CustomerService;

public class UserResources {
    private static final UserResources SINGLETON = new UserResources();

    private UserResources() {
    }

    public static UserResources getSingleton() {
        return SINGLETON;
    }

    private static final CustomerService customerService = CustomerService.getSingleton();

    public static void createUser(String fName, String lName, String email) {
        customerService.createUser(fName, lName, email);
    }

    public static boolean isEmailInSystem(String email) {
        return customerService.isEmailInSystem(email);
    }
}
