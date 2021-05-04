package net.fap.beecloud.scheduler;

public class TaskHandler {

    private Runnable runnable;
    private long start;
    private long delay;

    public TaskHandler(Runnable runnable, long delay) {
        start = System.currentTimeMillis();
        this.runnable = runnable;
        this.delay = delay;
    }

    public void handle() {
        runnable.run();
    }

    public boolean canHandle() {
        return delay <= System.currentTimeMillis() - start;
    }

}
