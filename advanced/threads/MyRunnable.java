package advanced.threads;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        var name = Thread.currentThread().getName();
        try {
            System.out.println("3 by " + name);
            Thread.sleep(1000);
            System.out.println("2 by " + name);
            Thread.sleep(1000);
            System.out.println("1 by " + name);
            Thread.sleep(1000);
            System.out.println("Finished by " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
