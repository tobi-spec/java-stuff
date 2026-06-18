package advanced.threads.deadlock;

import java.util.concurrent.CountDownLatch;

public class DeadLockExample {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 is in lock 1 waits for lock 2");
                sleep(100);
                synchronized (lock2) {
                    System.out.println("Thread 1 done");
                }
            }
        }, "Deadlock-Thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 is in lock 2 waits for lock 1");
                sleep(100);
                synchronized (lock1) {
                    System.out.println("Thread 2 done");
                }
            }
        }, "Deadlock-Thread-2");

        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        sleep(1000);
        DeadlockDetector.printDeadLocks();
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
