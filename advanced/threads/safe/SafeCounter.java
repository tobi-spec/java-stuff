package advanced.threads.safe;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounter {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized int getCounter() {
        return counter;
    }
}
