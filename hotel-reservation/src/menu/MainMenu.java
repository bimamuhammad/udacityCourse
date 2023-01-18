package menu;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;


public class MainMenu {

    Scanner input;
    private final Pattern regexInput = Pattern.compile("^[1-5]$");

    public MainMenu(Scanner input){
        this.input = input;
    }
    public void manageMainMenu(){
        String inputCommand = this.displayMainMenu();
        switch (inputCommand){
            case "1":
                this.findAndReserveARoom();
                break;
            case "2":
                this.seeAllReservations();
                break;
            case "3":
                this.createAnAccount();
                break;
            case "4":
                this.admin();
                break;
            case "5":
                this.exit();
                break;
            default:
                break;
        }
    }
    public String displayMainMenu(){
            while (true) {
                System.out.println("Welcome to the Hotel Reservation Application\n MAIN MENU:\n1. Find and reserve a room\n" +
                        "2. See my reservation\n" +
                        "3. Create an account\n" +
                        "4. Admin\n" +
                        "5. Exit\n" +
                        "-----------------------------------------------------------\n" +
                        "Please select a number for the menu option");
                try {
                    String input = this.input.nextLine();
                    if(regexInput.matcher(input).matches()){
                        return input;
                    }else{
                        System.out.println("Invalid input. Enter (1-5)");
                    }
                }catch (IllegalArgumentException ex){
                    System.out.println("Invalid input. Enter (1-5)");
                }
            }
    }

    final Pattern datePattern = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
    final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyy");
    private Date inputDate(String caption) {
        System.out.println(caption);
        String enteredDateDate = this.input.nextLine();
        try {
            if (datePattern.matcher(enteredDateDate).matches()) {
                return formatter.parse(enteredDateDate);
            } else {
                System.out.println("Invalid date");
                return this.inputDate(caption);
            }
        }catch (ParseException exception){
            System.out.println("Check your input");
            return this.inputDate(caption);
        }
    }

    private Boolean yesOrNo(String caption){
        System.out.println(caption);
        String response = this.input.nextLine();
        if(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n")){
            return response.equalsIgnoreCase("y");
        }else{
            System.out.println("Enter y or n");
            return this.yesOrNo(caption);
        }
    }
    private final Pattern roomNumberRegex = Pattern.compile("^[0-9]*$");
    private void findAndReserveARoom(){
        SimpleDateFormat outputFormat = new SimpleDateFormat("E MMM dd yyyy");
        Date checkInDate = this.inputDate("Enter Check In Date mm/dd/yyyy example 02/01/2020");

        Date checkOutDate = this.inputDate("Enter Check out Date mm/dd/yyyy example 02/01/2020");

        System.out.println("Available rooms");
        Collection<IRoom> availableRooms = HotelResource.findARoom(checkInDate, checkOutDate);
        boolean noRoomAvailable = availableRooms.isEmpty();

        if(noRoomAvailable){
            // Room exists but unavaileble,
            // Check for available rooms 7 days out from intended date
            Calendar c = Calendar.getInstance();
            c.setTime(checkInDate);
            c.add(Calendar.DATE, 7);
            Date newCheckInDate = c.getTime();
            c.setTime(checkOutDate);
            c.add(Calendar.DATE, 7);
            Date newCheckoutDate = c.getTime();
            Collection<IRoom> freeRooms7days = HotelResource.findARoom(newCheckInDate, newCheckoutDate);
            System.out.println("No room available within the specified date.\n" +
                    "The following are available on "+outputFormat.format(newCheckInDate)+" to " +
                    outputFormat.format(newCheckoutDate));
            for(IRoom freeRoom7days: freeRooms7days){
                System.out.println("Room: "+ freeRoom7days.getRoomNumber());
            }
            this.manageMainMenu();
        }else{
            for(IRoom room: availableRooms){
                System.out.println(room);
            }
        }

        if(this.yesOrNo("Would you like to book a room? y/n")){
            if(this.yesOrNo("Do you have an account with us? y/n")){
                System.out.println("Enter your email format name@domain.com");
                String email = this.input.nextLine();
                Customer customer = HotelResource.getCustomer(email);
                if(customer != null){
                    System.out.println("Which room number would you like to reserve?");
                    String roomNumber = this.input.nextLine();
                    while(!roomNumberRegex.matcher(roomNumber).matches()){
                        System.out.println("You have to enter a numerical value for room number");
                        roomNumber = this.input.nextLine();
                    }
                    Collection<IRoom> freeRooms = HotelResource.findARoom(checkInDate, checkOutDate);
                    boolean roomUnavailable = true;
                    boolean suggestionAvailabe = false;
                    for(IRoom freeRoom: freeRooms){
                        if(freeRoom.getRoomNumber().equals(roomNumber)){
                            roomUnavailable = false;
                            break;
                        }else if(!freeRoom.getRoomNumber().equals(roomNumber)){
                            suggestionAvailabe = true;
                        }
                    }
                    IRoom room = HotelResource.getRoom(roomNumber);
                    // this will help check out two things.
                    // 1) if room is already booked, then it is unavailable
                    // 2) if room is not even available because it does nto exist
                    if(room != null && !roomUnavailable) {
                        HotelResource.bookARoom(email, room, checkInDate, checkOutDate);
                        System.out.print("Reservation\n" +
                                customer.firstName + " " + customer.lastName + "\n" +
                                "Room: " + roomNumber + " - " + room.getRoomType() + "\n" +
                                "Price: $" + room.getRoomPrice() + " per night \n" +
                                "Checkin Date: " + outputFormat.format(checkInDate) + "\n" +
                                "Checkout Date: " + outputFormat.format(checkOutDate)+"\n");
                        this.manageMainMenu();
                    } else if (room != null && suggestionAvailabe) {
                        System.out.println("Room you selected is unavailable. \n However, the following rooms are available\n");
                        for(IRoom freeRoom: freeRooms){
                            System.out.println("Room: "+ freeRoom.getRoomNumber());
                        }
                        this.manageMainMenu();
                    } else{
                        System.out.println("Seems the room you entered does not exist");
                        this.manageMainMenu();
                    }
                }else{
                    System.out.println("Customer not found. You are being redirected to create an account");
                    this.createAnAccount();
                }
            }else{
                this.createAnAccount();
            }
        }else{
            this.manageMainMenu();
        }
    }

    final private String emailRegex="^(\\S+)@(.+).(.+)$";
    final Pattern emailPattern =  Pattern.compile(emailRegex);
    private void seeAllReservations() {
        SimpleDateFormat outputFormat = new SimpleDateFormat("E MMM dd yyyy");
        System.out.println("Enter your email address");
        String emailAddress = this.input.nextLine();
        while(!emailPattern.matcher(emailAddress).matches()){
            System.out.println("Enter valid email address name@domain.com");
            emailAddress = this.input.nextLine();
        }
        Collection<Reservation> reservations = HotelResource.getCustomersReservations(emailAddress);
        if(reservations.isEmpty()){
            System.out.println("You have no reservations at this time");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println("----------------------------------------");
                System.out.println(outputFormat.format(reservation.getCheckInDate()) + " to " +
                        outputFormat.format(reservation.getCheckOutDate()));
            }
        }
        this.manageMainMenu();
    }

    final Pattern namePattern = Pattern.compile("^\\S*$");
    private void createAnAccount(){
        System.out.println("Create an account");
        System.out.println("Enter email format: name@domain.com");
        String email = this.input.nextLine();
        while(!emailPattern.matcher(email).matches()){
            System.out.println("Enter valid email address");
            email = this.input.nextLine();
        }
        System.out.println("Enter FirstName");
        String firstName = this.input.nextLine();
        while(!namePattern.matcher(firstName).matches()){
            System.out.println("Enter valid name. NO Spaces");
            firstName = this.input.nextLine();
        }
        System.out.println("Enter LastName");
        String lastName = this.input.nextLine();
        while(!namePattern.matcher(lastName).matches()){
            System.out.println("Enter valid name. No Spaces");
            lastName = this.input.nextLine();
        }
        try {
            HotelResource.createACustomer(email, firstName, lastName);
            this.manageMainMenu();
        }catch(IllegalArgumentException illegalArgumentException){
            System.out.println("Invalid email address. Restarting");
            this.input.nextLine();
            this.createAnAccount();
        }
    }
    private void admin(){
        AdminMenu adminMenu = new AdminMenu(this.input, this);
        adminMenu.manageAdminMenu();
    }
    private void exit(){
        System.out.println("Exiting");
    }

}
