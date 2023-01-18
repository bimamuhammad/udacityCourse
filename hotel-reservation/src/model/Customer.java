package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer{
    public String firstName;
    public String lastName;
    public String email;

    final private String emailRegex="^(\\S+)@(.+).(.+)$";
    Pattern emailPattern = Pattern.compile(emailRegex);
    public Customer(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        if(emailPattern.matcher(email).matches()) {
            this.email = email;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString(){
        return "FirstName: "+ this.firstName+" LastName "+this.lastName+" Email "+this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) && Objects.equals(emailRegex, customer.emailRegex) && Objects.equals(emailPattern, customer.emailPattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, emailRegex, emailPattern);
    }

}
