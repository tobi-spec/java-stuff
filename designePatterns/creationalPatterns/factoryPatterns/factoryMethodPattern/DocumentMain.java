package designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern;

import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.factory.CSVDocumentFactory;
import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.factory.PDFDocumentFactory;
import designePatterns.creationalPatterns.factoryPatterns.factoryMethodPattern.factory.WordDocumentFactory;

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
