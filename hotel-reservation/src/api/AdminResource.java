package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    static CustomerService customer_service = CustomerService.getInstance();
    static ReservationService reservation_service = ReservationService.getInstance();

    private static AdminResource single_instance = null;

    private  AdminResource(){}

    public static AdminResource getInstance(){
        if(single_instance == null){
            single_instance = new AdminResource();
        }
        return single_instance;
    }
    public Customer getCustomer(String email){
        return customer_service.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for(IRoom room: rooms) {
            reservation_service.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservation_service.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customer_service.getAllCustomers();
    }

    public void displayAllReservations(){
        reservation_service.printAllReservation();
    }
    public void displayAllRooms(){
        reservation_service.printAllRooms();
    }
}
