package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static Set<IRoom> rooms = new HashSet<IRoom>();
    static List<Reservation> reservations = new ArrayList<Reservation>();
    public static void addRoom(IRoom room){
        rooms.add(room);
    }

    public static IRoom getARoom(String roomId){
        for(IRoom room: rooms){
            if(Objects.equals(room.getRoomNumber(), roomId)){
                return room;
            }
        }
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkoutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkoutDate);
        reservations.add(reservation);
        return reservation;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> customerReservations = new ArrayList<Reservation>();
        for(Reservation reservation: reservations){
            if(reservation.getCustomer().equals(customer)){
                customerReservations.add( reservation);
            }
        }
        return customerReservations;
    }

    public static void printAllReservation(){
        for(Reservation reservation: reservations ){
            System.out.println(reservation);
        }
    }
    public static Collection<Reservation> getAllReservation(){return reservations;}
    public static Collection<IRoom> getAllRooms(){return rooms;}

    public static void printAllRooms(){
        for(IRoom room: rooms ){
            System.out.println(room);
        }
    }
}
