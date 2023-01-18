package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HotelResource {
    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void  createACustomer(String email, String firstName, String lastName){
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber){
        try {
            return ReservationService.getARoom(roomNumber);
        }catch(NullPointerException ex){
            return null;
        }
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer =  HotelResource.getCustomer(customerEmail);
        return ReservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer =  HotelResource.getCustomer(customerEmail);
        return ReservationService.getCustomersReservation(customer);
    }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkout){
        List<IRoom> freeRooms = new ArrayList<>();

        // Use the reservation to populate isFree in rooms
        List<Reservation> reservation = (List<Reservation>) ReservationService.getAllReservation();
        Collection<IRoom> allRooms = ReservationService.getAllRooms();
        for(Reservation ind: reservation ){
            Room bookedRoom = (Room) ind.getRoom();
            bookedRoom.setIsFree(checkout.compareTo(ind.getCheckInDate()) <= 0 || ind.getCheckOutDate().compareTo(checkIn) <= 0);
            for(IRoom room: allRooms){
                if(room.getRoomNumber().equals(bookedRoom.getRoomNumber())){
                    allRooms.remove(room);
                    allRooms.add(bookedRoom);
                    break;
                }
            }
        }
        for(IRoom individualRoom: allRooms){
            if(individualRoom.isFree()){
                freeRooms.add(individualRoom);
            }
        }

        return freeRooms;
    }
}
