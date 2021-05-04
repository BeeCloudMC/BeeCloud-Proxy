package net.fap.beecloud.scheduler;


public class TaskManager {
    private static ThreadLocal<TaskQueue> local = new ThreadLocal<>();

    public static void addMessage(TaskHandler message) {
        local.get().push(message);
    }

    public static void prepare() {
        TaskQueue queue = local.get();
        if (queue != null) {
            throw new RuntimeException();
        }
        local.set(new TaskQueue());
    }

    public static void loop() {
        TaskQueue messageQueue = local.get();
        TaskHandler message;
        while ((message = messageQueue.pop()) != null) {
            if (message.canHandle()) {
                messageQueue.remove(message);
                message.handle();
            }
        }
    }

}