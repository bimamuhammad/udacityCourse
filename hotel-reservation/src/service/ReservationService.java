package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationService {
    private Set<IRoom> rooms = new HashSet<>();
    static List<Reservation> reservations = new ArrayList<>();
    private static ReservationService single_instance = null;
    public static ReservationService getInstance(){
        if(single_instance == null){
            single_instance = new ReservationService();
        }
        return single_instance;
    }
    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public IRoom getARoom(String roomId){
        for(IRoom room: rooms){
            if(Objects.equals(room.getRoomNumber(), roomId)){
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkoutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkoutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> customerReservations = new ArrayList<>();
        for(Reservation reservation: reservations){
            if(reservation.getCustomer().equals(customer)){
                customerReservations.add( reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservation(){
        if(reservations.isEmpty()){
            System.out.println("There are no reservations at this time");
        }else {
            for (Reservation reservation : reservations) {
                System.out.println("----------------------------------------");
                System.out.println(reservation);
            }
        }
    }
    public Collection<Reservation> getAllReservation(){return reservations;}
    public Collection<IRoom> getAllRooms(){return rooms;}

    public void printAllRooms(){
        if(rooms.isEmpty()){
            System.out.println("There are no rooms at this time");
        } else {
            for (IRoom room : rooms) {
                System.out.println("----------------------------------------");
                System.out.println(room);
            }
        }
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkout){
        List<IRoom> freeRooms = new ArrayList<>();

        // Use the reservation to populate isFree in rooms
        for(Reservation ind: reservations ){
            Room bookedRoom = (Room) ind.getRoom();
            bookedRoom.setIsFree(checkout.compareTo(ind.getCheckInDate()) < 0 || ind.getCheckOutDate().compareTo(checkIn) < 0);
            for(IRoom room: rooms){
                if(room.getRoomNumber().equals(bookedRoom.getRoomNumber())){
                    rooms.remove(room);
                    rooms.add(bookedRoom);
                    break;
                }
            }
        }
        for(IRoom individualRoom: rooms){
            if(individualRoom.isFree()){
                freeRooms.add(individualRoom);
            }
        }

        return freeRooms;
    }

    SimpleDateFormat outputFormat = new SimpleDateFormat("E MMM dd yyyy");
    public void recommendAltRoom(Date checkIn, Date checkout){
        Calendar c = Calendar.getInstance();
        c.setTime(checkIn);
        c.add(Calendar.DATE, 7);
        Date newCheckInDate = c.getTime();
        c.setTime(checkout);
        c.add(Calendar.DATE, 7);
        Date newCheckoutDate = c.getTime();
        System.out.println("No room available within the specified date.\n" +
                "The following are available on "+outputFormat.format(newCheckInDate)+" to " +
                outputFormat.format(newCheckoutDate));
        Collection<IRoom> recommendedRooms =  findARoom(newCheckInDate, newCheckoutDate);
        for(IRoom recommendedRoom: recommendedRooms){
            System.out.println("Room: "+ recommendedRoom.getRoomNumber());
        }

    }
}
