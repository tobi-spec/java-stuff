package com.example.factoryPatterns.factoryMethodPattern.factory;

import com.example.factoryPatterns.factoryMethodPattern.model.Document;
import com.example.factoryPatterns.factoryMethodPattern.model.WordDocument;

public class WordDocumentFactory extends DocumentFactory{

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
