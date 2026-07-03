package com.example.factoryPatterns.simpleFactoryPattern;

import com.example.factoryPatterns.simpleFactoryPattern.model.Bike;
import com.example.factoryPatterns.simpleFactoryPattern.model.Bus;
import com.example.factoryPatterns.simpleFactoryPattern.model.Car;
import com.example.factoryPatterns.simpleFactoryPattern.model.Vehicle;

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
