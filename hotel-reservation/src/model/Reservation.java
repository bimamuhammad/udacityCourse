package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation{
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return this.customer.toString() +" "+ this.room.toString()+
                " CheckIn "+ formatter.format(this.checkInDate) + " Checkout "+formatter.format(this.checkOutDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(customer, that.customer) && Objects.equals(room, that.room) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }
}
