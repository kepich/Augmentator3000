package utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private static final int AMOUNT_OF_THREADS = 1;
    private static ConcurrentHashMap<Integer, ExecutorService> executors = new ConcurrentHashMap<>();

    public synchronized static void runTask(Runnable runnable, int priority) {
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

    public synchronized static void runTask(Runnable runnable) {
        runTask(runnable, -1);
    }

    public synchronized static void shutdownNow(){
        executors.values().forEach(ExecutorService::shutdownNow);
        executors.clear();
    }
}
