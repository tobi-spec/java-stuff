package advanced.threads.unsafe;

public class NonThreadSafeCounter {

    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter unsafeCounter = new UnsafeCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Final count (Unsafe): " + unsafeCounter.getCounter());
    }
}
