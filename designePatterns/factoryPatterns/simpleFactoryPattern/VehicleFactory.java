package designePatterns.factoryPatterns.simpleFactoryPattern;

import designePatterns.factoryPatterns.simpleFactoryPattern.model.Bike;
import designePatterns.factoryPatterns.simpleFactoryPattern.model.Bus;
import designePatterns.factoryPatterns.simpleFactoryPattern.model.Car;
import designePatterns.factoryPatterns.simpleFactoryPattern.model.Vehicle;

public class VehicleFactory {

    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType == null) {
            return null;
        }

        switch (vehicleType.toUpperCase()) {
            case "CAR":
                return new Car();
            case "BIKE":
                return new Bike();
            case "BUS":
                return new Bus();
            default:
                return null;
        }
    }
}
