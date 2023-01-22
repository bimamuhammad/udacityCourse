package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HotelResource {

    static CustomerService customer_service = CustomerService.getInstance();
    static ReservationService reservation_service = ReservationService.getInstance();
    private static HotelResource single_instance = null;

    private  HotelResource(){}

    public static HotelResource getInstance(){
        if(single_instance == null){
            single_instance = new HotelResource();
        }
        return single_instance;
    }
    public Customer getCustomer(String email){
        return customer_service.getCustomer(email);
    }

    public void  createACustomer(String email, String firstName, String lastName){
        customer_service.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        try {
            return reservation_service.getARoom(roomNumber);
        }catch(NullPointerException ex){
            return null;
        }
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer =  getCustomer(customerEmail);
        return reservation_service.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer =  getCustomer(customerEmail);
        return reservation_service.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkout){
        return reservation_service.findARoom(checkIn, checkout);
    }

    public void recommendAltRoom(Date checkIn, Date checkout){
        reservation_service.recommendAltRoom(checkIn, checkout);
    }

}
