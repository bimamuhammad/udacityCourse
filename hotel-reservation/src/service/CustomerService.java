package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    static List<Customer> customers = new ArrayList<Customer>();
    public static void addCustomer(String email, String firstName, String lastName){
        customers.add(new Customer(firstName, lastName, email));
    }

    public static Customer getCustomer(String customerEmail){
        for(Customer customer: customers){
            if(customer.email.equals(customerEmail)){
                return customer;
            }
        }
        return null;
    }

    public static Collection<Customer> getAllCustomers(){
        return customers;
    }
    public static void printAllCustomers(){
        for(Customer customer: customers ){
            System.out.println(customer);
        }
    }
}
