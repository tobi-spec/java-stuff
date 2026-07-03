package com.example.factoryPatterns.factoryMethodPattern;

import com.example.factoryPatterns.factoryMethodPattern.factory.CSVDocumentFactory;
import com.example.factoryPatterns.factoryMethodPattern.factory.PDFDocumentFactory;
import com.example.factoryPatterns.factoryMethodPattern.factory.WordDocumentFactory;

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
