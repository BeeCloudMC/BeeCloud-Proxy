package net.fap.beecloud.scheduler;

import java.util.PriorityQueue;

public final class Scheduler {
	private final PriorityQueue<TaskHandler> queue = new PriorityQueue<>();
	private int id = 0;

	public Scheduler() {

	}

	public TaskHandler schedule(Task task) {
		TaskHandler handler = new TaskHandler(task, this.id++);
		this.queue.add(handler);
		return handler;
	}

	public void heartbeat() {
		for (TaskHandler h : queue) {
			h.getTask().run();
		}
		queue.clear();
		this.id = 0;
	}
}
