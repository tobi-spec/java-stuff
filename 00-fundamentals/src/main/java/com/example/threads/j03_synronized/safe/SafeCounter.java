package com.example.threads.j03_synronized.safe;

public class SafeCounter {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized int getCounter() {
        return counter;
    }
}
