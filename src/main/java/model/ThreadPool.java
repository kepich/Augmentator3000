package model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private static final int AMOUNT_OF_THREADS = 2;
    private static final ConcurrentHashMap<Integer, ExecutorService> executors = new ConcurrentHashMap<>();

    public static synchronized void runTask(Runnable runnable, int priority) {
        if (!executors.containsKey(priority)) {
            executors.put(priority, Executors.newWorkStealingPool(AMOUNT_OF_THREADS));
        }

        while (true) {
            try {
                executors.get(priority).submit(runnable);
                break;
            } catch (Exception ignored) {
            }
        }
    }

    public static synchronized void runTask(Runnable runnable) {
        runTask(runnable, -1);
    }

    public static synchronized void shutdownNow() {
        executors.values().forEach(ExecutorService::shutdownNow);
        executors.clear();
    }
}
