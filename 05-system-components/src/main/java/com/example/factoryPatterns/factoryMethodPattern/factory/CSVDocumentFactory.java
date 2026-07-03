package com.example.factoryPatterns.factoryMethodPattern.factory;

import com.example.factoryPatterns.factoryMethodPattern.model.CSVDocument;
import com.example.factoryPatterns.factoryMethodPattern.model.Document;

public class CSVDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new CSVDocument();
    }
}
