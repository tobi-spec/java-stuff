package designePatterns.factoryPatterns.factoryMethodPattern.factory;

import designePatterns.factoryPatterns.factoryMethodPattern.model.Document;
import designePatterns.factoryPatterns.factoryMethodPattern.model.PDFDocument;

public class PDFDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}
