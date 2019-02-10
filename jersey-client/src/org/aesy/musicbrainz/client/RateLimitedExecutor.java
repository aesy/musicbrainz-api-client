package org.aesy.musicbrainz.client;

import org.jetbrains.annotations.NotNull;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* package-private */ final class RateLimitedExecutor
    implements Executor, Runnable {

    @NotNull
    private final ThreadFactory threadFactory;

    @NotNull
    private final Queue<Runnable> runnables;

    @NotNull
    private final Executor queueExecutor;

    @NotNull
    private final Executor taskExecutor;

    @NotNull
    private final AtomicBoolean isRunning;

    private final long millisecondsBetweenRuns;

    private long lastRun;

    /* package-private */ RateLimitedExecutor(
        double runs,
        @NotNull TimeUnit unit
    ) {
        this.threadFactory = new DeamonThreadFactory(getClass().getSimpleName());
        this.runnables = new ConcurrentLinkedDeque<>();
        this.queueExecutor = new ThreadPoolExecutor(
            0, 1, 1L, TimeUnit.SECONDS,
            new SynchronousQueue<>(), this.threadFactory, new ThreadPoolExecutor.DiscardPolicy());
        this.taskExecutor = new ThreadPoolExecutor(
            0, Integer.MAX_VALUE, 1L, TimeUnit.SECONDS,
            new SynchronousQueue<>(), this.threadFactory, new ThreadPoolExecutor.AbortPolicy());
        this.isRunning = new AtomicBoolean(false);
        this.millisecondsBetweenRuns = (long) (runs * TimeUnit.MILLISECONDS.convert(1, unit));
    }

    @Override
    public void run() {
        while (isRunning.get() && !runnables.isEmpty()) {
            Runnable runnable = runnables.poll();

            if (runnable == null) {
                break;
            }

            long now = System.currentTimeMillis();
            long elapsedMillisecondsSinceLastRun = now - lastRun;
            long waitTimeMilliseconds = millisecondsBetweenRuns - elapsedMillisecondsSinceLastRun;

            if (waitTimeMilliseconds > 0) {
                try {
                    Thread.sleep(waitTimeMilliseconds);
                } catch (InterruptedException exception) {
                    break;
                }
            }

            taskExecutor.execute(runnable);
            lastRun = System.currentTimeMillis();
        }

        isRunning.set(false);
    }

    @Override
    public synchronized void execute(@NotNull Runnable runnable) {
        runnables.add(runnable);

        if (isRunning.compareAndSet(false, true)) {
            queueExecutor.execute(this);
        }
    }

    private static final class DeamonThreadFactory
        implements ThreadFactory {

        @NotNull
        private final AtomicInteger threadNumber;

        @NotNull
        private final String namePrefix;

        private DeamonThreadFactory(@NotNull String namePrefix) {
            this.threadNumber = new AtomicInteger(0);
            this.namePrefix = String.format("%s-thread-", namePrefix);
        }

        @NotNull
        public Thread newThread(@NotNull Runnable runnable) {
            int number = threadNumber.incrementAndGet();
            Thread thread = new Thread(runnable, namePrefix + number);
            thread.setDaemon(true);

            return thread;
        }

    }

}
