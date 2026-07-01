package advanced.threads;

public class PrintThreads {
    public static void main(String[] args) {
        System.out.println(Thread.activeCount());

        Thread.getAllStackTraces()
                .keySet()
                .forEach(thread ->
                        System.out.println(thread.getName()
                                + " / group=" + thread.getThreadGroup().getName()
                                + " / daemon=" + thread.isDaemon()
                                + " / priority=" + thread.getPriority()));

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getState());
    }

}
