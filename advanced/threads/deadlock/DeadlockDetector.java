package advanced.threads.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector {
    public static void printDeadLocks() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

        long[] deadlockedThreadIds = threadBean.findDeadlockedThreads();

        if (deadlockedThreadIds == null) {
            System.out.println("No deadlocks found.");
            return;
        }

        ThreadInfo[] threadInfos =
                threadBean.getThreadInfo(deadlockedThreadIds, true, true);

        System.out.println("Deadlock detected!");

        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo);
        }
    }
}
