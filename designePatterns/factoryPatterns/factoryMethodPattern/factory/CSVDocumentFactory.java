package designePatterns.factoryPatterns.factoryMethodPattern.factory;

import designePatterns.factoryPatterns.factoryMethodPattern.model.CSVDocument;
import designePatterns.factoryPatterns.factoryMethodPattern.model.Document;

public class CSVDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new CSVDocument();
    }
}
