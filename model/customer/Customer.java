package model.customer;

public class Customer {
    private String fName;
    private String lName;
    private String email;

    public Customer(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }


    public String getfName() {
        return fName;
    }

    public String setfName(String fName) {
        return this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public String setlName(String lName) {
        return this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        return this.email = email;
    }

    @Override
    public String toString() {
        return fName + " " + lName + " " + email;
    }
}
