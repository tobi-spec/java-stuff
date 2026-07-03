package com.example.factoryPatterns.factoryMethodPattern.model;

public class CSVDocument implements Document{

    @Override
    public void open() {
        System.out.println("Open CSV document");
    }
}
