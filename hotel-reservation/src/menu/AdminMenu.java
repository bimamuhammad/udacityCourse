package menu;

import api.AdminResource;
import model.FreeRoom;
import model.IRoom;
import model.RoomType;
import service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdminMenu {

    Scanner input;
    MainMenu mainMenu;
    public AdminMenu(Scanner input, MainMenu mainMenu){
        this.input = input;
        this.mainMenu = mainMenu;
    }

    public void manageAdminMenu(){
        String inputCommand = this.displayAdminMenu();
        switch (inputCommand){
            case "1":
                this.seeAllCustomers();
                break;
            case "2":
                this.seeAllRooms();
                break;
            case "3":
                this.seeAllReservations();
                break;
            case "4":
                this.addARoom();
                break;
            case "5":
                this.backToMain();
                break;
            default:
                break;
        }
    }

    private void backToMain() {
        this.mainMenu.manageMainMenu();
    }
    private final Pattern roomTypeRegex = Pattern.compile("^[1-2]$");
    private void addARoom() {
        try {
            System.out.println("Enter room number");
            String roomId = this.input.nextLine();

            System.out.println("Enter price per night");
            Double roomPrice = this.input.nextDouble();
            this.input.nextLine();

            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            RoomType roomType;
            while(true){
                String inputValue = this.input.nextLine();
                if(roomTypeRegex.matcher(inputValue).matches()){
                    roomType = inputValue.equals("1")? RoomType.SINGLE : RoomType.DOUBLE;
                    break;
                }else{
                    System.out.println("Enter a 1 or 2 to continue");
                }
            }


            List<IRoom> room = new ArrayList<IRoom>();
            room.add(new FreeRoom(roomId, roomPrice, roomType));
            AdminResource.addRoom(room);
            Boolean waiting = true;
            System.out.println("Would you like to add another room y/n");
            while(waiting){
                String inputValue = this.input.nextLine();
                if(inputValue.equalsIgnoreCase("y")){
                    this.addARoom();
                    waiting = false;
                } else if (inputValue.equalsIgnoreCase("n")) {
                    this.manageAdminMenu();
                    waiting = false;
                }else{
                    System.out.println("Please enter Y (Yes) or N (No)");
                }
            }
        }catch (Exception ex){
            System.out.println("Enter Valid values");
            this.input.nextLine();
            this.addARoom();
        }


    }

    private void seeAllReservations() {
        AdminResource.displayAllReservations();
        this.manageAdminMenu();
    }

    private void seeAllRooms() {
        AdminResource.displayAllRooms();
        this.manageAdminMenu();
    }

    private void seeAllCustomers() {
        CustomerService.printAllCustomers();
        this.manageAdminMenu();
    }

    private final Pattern regexInput = Pattern.compile("^[1-5]$");
    public String displayAdminMenu() {
        while (true) {
            System.out.println("ADMIN MENU\n1. See all Customers\n" +
                    "2. See all Rooms\n" +
                    "3. See all Reservations\n" +
                    "4. Add a Room\n" +
                    "5. Back to Main Menu\n" +
                    "-----------------------------------------------------------\n" +
                    "Please select a number for the menu option");
            try {
                String input = this.input.nextLine();
                if (regexInput.matcher(input).matches()) {
                    return input;
                }else{
                    System.out.println("Invalid input. Enter (1-5)");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid input. Enter (1-5)");
            }
        }
    }
}

