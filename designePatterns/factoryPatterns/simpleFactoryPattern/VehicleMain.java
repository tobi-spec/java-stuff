package designePatterns.factoryPatterns.simpleFactoryPattern;

import designePatterns.factoryPatterns.simpleFactoryPattern.model.Vehicle;

public class VehicleMain {

    public static void main(String[] args) {

        VehicleFactory vehicleFactory = new VehicleFactory();

        Vehicle car = vehicleFactory.getVehicle("Car");
        car.drive();

        Vehicle bike = vehicleFactory.getVehicle("Bike");
        bike.drive();

        Vehicle bus = vehicleFactory.getVehicle("Bus");
        bus.drive();
    }
}
