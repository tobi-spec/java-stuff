package designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.factory;

import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.model.CSVDocument;
import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.model.Document;

public class CSVDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new CSVDocument();
    }
}
