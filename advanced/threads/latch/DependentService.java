package advanced.threads.latch;

import java.util.concurrent.CountDownLatch;

public class DependentService implements Runnable {
    private final CountDownLatch latch;

    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("DependentService is running");
        latch.countDown();
    }
}
