package designePatterns.factoryPatterns.simpleFactoryPattern.model;

public class Bike implements Vehicle{

    @Override
    public void drive() {
        System.out.println("Drive a bike");
    }
}
