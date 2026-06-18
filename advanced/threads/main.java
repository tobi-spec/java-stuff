package advanced.threads;

public class main {
    public static void main(String[] args) {
        System.out.println(Thread.activeCount());

        Thread.getAllStackTraces()
                .keySet()
                .forEach(thread ->
                        System.out.println(thread.getName()
                                + " / group=" + thread.getThreadGroup().getName()
                                + " / daemon=" + thread.isDaemon()
                                + " / priority=" + thread.getPriority()));
    }
}
