package designePatterns.factoryPatterns.factoryMethodPattern.model;

public class WordDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
