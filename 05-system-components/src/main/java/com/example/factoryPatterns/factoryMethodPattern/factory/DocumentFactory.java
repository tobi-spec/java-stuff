package com.example.factoryPatterns.factoryMethodPattern.factory;

import com.example.factoryPatterns.factoryMethodPattern.model.Document;

public abstract class DocumentFactory {

    public abstract Document createDocument();

    public void openDocument() {
        Document document = createDocument();
        document.open();
    }
}
