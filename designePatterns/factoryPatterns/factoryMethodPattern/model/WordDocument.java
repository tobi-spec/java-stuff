package designePatterns.factoryPatterns.factoryMethodPattern.model;

public class WordDocument implements Document{

    @Override
    public void open() {
        System.out.println("Open Word document");
    }
}
