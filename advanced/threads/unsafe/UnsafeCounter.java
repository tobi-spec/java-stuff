package advanced.threads.unsafe;

public class UnsafeCounter {
    private int counter = 0;

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
