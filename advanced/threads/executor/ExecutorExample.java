package advanced.threads.executor;

import advanced.threads.safe.SafeCounter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {

    public static void main(String[] args) throws InterruptedException {
        SafeCounter safeCounter = new SafeCounter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                safeCounter.increment();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(task);
        executorService.submit(task);

        executorService.shutdown();

        if(executorService.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("Final count (safe): " + safeCounter.getCounter());
        } else {
            System.out.println("Tasks did not finish in time.");
        }
    }
}
