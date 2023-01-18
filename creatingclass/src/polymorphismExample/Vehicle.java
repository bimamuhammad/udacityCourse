package polymorphismExample;

public abstract class Vehicle {
    protected String start;
    protected  String stop;
    protected  String speed;
    protected String direction;

    public Vehicle(String start, String stop, String speed, String direction) {
        this.start = start;
        this.stop = stop;
        this.speed = speed;
        this.direction = direction;
    }

    public void start(){System.out.println(start);}
    public void stop(){System.out.println(stop);}
    public abstract void speed();
    public void direction(){System.out.println(direction);}
}
