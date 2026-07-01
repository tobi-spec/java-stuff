package advanced.threads.create;

public class CreateThreads {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        MyRunnable myRunnable = new MyRunnable();
        Thread myThread1 = new Thread(myRunnable);
        myThread1.start();
    }
}
