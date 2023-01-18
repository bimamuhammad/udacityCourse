import model.Customer;

public class Driver {
    public static void main(String[] args){
        try {
            Customer customer = new Customer("Ramon", "Samosa", "ramon@samosa.com");
            System.out.println(customer);
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("You need to enter a correct email address name@domain.com");
        }
    }
}
