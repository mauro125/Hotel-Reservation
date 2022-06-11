package model.reservation;

import model.customer.Customer;
import model.room.IRoom;

import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    public IRoom getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Customer: " + customer.toString() + "\nRoom: " + room.toString() + "\nCheckIn Date: " + checkInDate
                + "\nCheckOut Date: " + checkOutDate;
    }

}

