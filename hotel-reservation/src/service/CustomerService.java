package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    static List<Customer> customers = new ArrayList<Customer>();

    private static CustomerService single_instance = null;
    private CustomerService(){}

    public static CustomerService getInstance(){
        if(single_instance == null){
            single_instance = new CustomerService();
        }
        return single_instance;
    }
    public void addCustomer(String email, String firstName, String lastName){
        customers.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        for(Customer customer: customers){
            if(customer.email.equals(customerEmail)){
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }
    public void printAllCustomers(){
        for(Customer customer: customers ){
            System.out.println(customer);
        }
    }
}
