package polymorphismExample;

public class VehicleTester {
    public static void main(String[] args){
        Vehicle[] vehicles = new Vehicle[3];

        Car car = new Car();
        vehicles[0] = car;

        Boat boat = new Boat();
        vehicles[1] = boat;

        Plane plane = new Plane();
        vehicles[2] = plane;

        for(Vehicle vehicle: vehicles){
            vehicle.speed();
        }
    }
}
