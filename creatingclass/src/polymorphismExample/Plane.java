package polymorphismExample;

public class Plane extends Vehicle{

    public Plane() {
        super("Plane start", "Plane stop", "Plane speed", "Plane direction");
    }

    @Override
    public void speed() {
        System.out.println("300");
    }
}
