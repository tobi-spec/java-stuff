package designePatterns.factoryPatterns.factoryMethodPattern.model;

public class PDFDocument implements Document{

    @Override
    public void open() {
        System.out.println("Open PDF document");
    }
}
