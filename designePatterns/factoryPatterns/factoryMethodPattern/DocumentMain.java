package designePatterns.factoryPatterns.factoryMethodPattern;

import designePatterns.factoryPatterns.factoryMethodPattern.model.CSVDocumentFactory;
import designePatterns.factoryPatterns.factoryMethodPattern.model.PDFDocumentFactory;
import designePatterns.factoryPatterns.factoryMethodPattern.model.WordDocumentFactory;

public class DocumentMain {

    public static void main(String[] args) {

        WordDocumentFactory wordDocumentFactory = new WordDocumentFactory();
        wordDocumentFactory.openDocument();

        PDFDocumentFactory pdfDocumentFactory = new PDFDocumentFactory();
        pdfDocumentFactory.openDocument();

        CSVDocumentFactory csvDocumentFactory = new CSVDocumentFactory();
        csvDocumentFactory.createDocument().open();
    }
}
