package designePatterns.factoryPatterns.factoryMethodPattern.model;

import designePatterns.factoryPatterns.factoryMethodPattern.model.Document;

public abstract class DocumentFactory {

    public abstract Document createDocument();

    public void openDocument() {
        Document document = createDocument();
        document.open();
    }
}
