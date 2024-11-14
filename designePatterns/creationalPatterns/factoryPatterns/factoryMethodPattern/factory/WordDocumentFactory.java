package designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.factory;

import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.model.Document;
import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.model.WordDocument;

public class WordDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
