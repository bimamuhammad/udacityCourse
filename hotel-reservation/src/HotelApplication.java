import menu.MainMenu;

import java.util.Scanner;

public class HotelApplication {
    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){

            MainMenu mainmenu = new MainMenu(input);
            mainmenu.manageMainMenu();
        }
    }
}