package designePatterns.factoryPatterns.simpleFactoryPattern.model;

public class Bus implements Vehicle{

    @Override
    public void drive() {
        System.out.println("Drive a bus");
    }
}
