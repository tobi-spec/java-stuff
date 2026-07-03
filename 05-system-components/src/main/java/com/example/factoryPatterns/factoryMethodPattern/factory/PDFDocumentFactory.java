package com.example.factoryPatterns.factoryMethodPattern.factory;

import com.example.factoryPatterns.factoryMethodPattern.model.Document;
import com.example.factoryPatterns.factoryMethodPattern.model.PDFDocument;

public class PDFDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}
