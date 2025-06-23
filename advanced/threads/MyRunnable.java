package advanced.threads;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("3");
            Thread.sleep(1000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("1");
            Thread.sleep(1000);
            System.out.println("Finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
