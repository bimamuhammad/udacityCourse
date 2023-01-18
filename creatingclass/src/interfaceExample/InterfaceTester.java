package interfaceExample;

public class InterfaceTester {

    public static void main(String[] args){
        Car car = new Car( "35", "green");

        System.out.println(car.getType());
        System.out.println(car.getSpeed());
        System.out.println(car.getColor());
    }
}
