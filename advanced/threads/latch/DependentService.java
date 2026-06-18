package advanced.threads.latch;

import java.util.concurrent.CountDownLatch;
import java.util.Random;
import static java.lang.Thread.sleep;

public class DependentService implements Runnable {
    private final CountDownLatch latch;

    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("DependentService is running");
        Random rand = new Random();
        try {
            sleep(rand.nextInt(10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        latch.countDown();
    }
}
