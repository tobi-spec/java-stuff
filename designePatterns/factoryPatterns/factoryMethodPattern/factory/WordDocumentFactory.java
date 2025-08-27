package designePatterns.factoryPatterns.factoryMethodPattern.factory;

import designePatterns.factoryPatterns.factoryMethodPattern.model.Document;
import designePatterns.factoryPatterns.factoryMethodPattern.model.WordDocument;

public class WordDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
