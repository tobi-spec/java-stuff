package designePatterns.creationalPatterns.factoryPatterns.simpleFactoryPattern.model;

public class Car implements Vehicle{

    @Override
    public void drive() {
        System.out.println("Drive a car");
    }
}
