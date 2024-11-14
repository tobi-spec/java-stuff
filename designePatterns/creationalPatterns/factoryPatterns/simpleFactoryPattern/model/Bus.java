package designePatterns.creationalPatterns.factoryPatterns.simpleFactoryPattern.model;

public class Bus implements Vehicle{

    @Override
    public void drive() {
        System.out.println("Drive a bus");
    }
}
