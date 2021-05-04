package net.fap.beecloud.scheduler;


import java.util.ArrayList;
import java.util.List;

public class TaskQueue {
    private List<TaskHandler> messages = new ArrayList<>();
    private int cursor = -1;

    public void push(TaskHandler message) {
        messages.add(message);
    }

    public void remove(TaskHandler message) {
        messages.remove(message);
        cursor--;
    }

    public TaskHandler pop() {
        TaskHandler message = null;
        if (!messages.isEmpty()) {
            message = messages.get(++cursor);
            if (cursor == messages.size() - 1) {
                cursor = -1;
            }
        }
        return message;
    }
}
