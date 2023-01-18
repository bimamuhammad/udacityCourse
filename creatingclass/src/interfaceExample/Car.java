package interfaceExample;

public class Car implements Vehicle{
    private String type;
    private String speed;
    private String color;

    public Car(String speed, String color){
        this.type = "Car";
        this.speed = speed;
        this.color = color;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getSpeed() {
        return this.speed;
    }

    @Override
    public String getColor() {
        return this.color;
    }
}
