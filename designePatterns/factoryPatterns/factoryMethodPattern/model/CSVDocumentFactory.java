package designePatterns.factoryPatterns.factoryMethodPattern.model;

public class CSVDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new CSVDocument();
    }
}
