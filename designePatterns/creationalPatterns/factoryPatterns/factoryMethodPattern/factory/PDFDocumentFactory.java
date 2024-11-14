package designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.factory;

import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.model.Document;
import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.model.PDFDocument;

public class PDFDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}
