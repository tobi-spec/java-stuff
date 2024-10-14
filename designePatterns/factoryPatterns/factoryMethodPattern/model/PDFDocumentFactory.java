package designePatterns.factoryPatterns.factoryMethodPattern.model;

public class PDFDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}
