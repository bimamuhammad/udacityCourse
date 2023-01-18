package mapExample;

public class Person {
    String name;
    String email;

    public Person(String name, String email){
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name is " + this.name + " Email address is" + this.email;
    }
}
