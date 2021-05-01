package net.fap.beecloud.scheduler;

public class TaskHandler {
	private final int id;
	private final Task task;
	private boolean cancelled = false;

	public TaskHandler(Task task, int id) {
		this.task = task;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Task getTask() {
		return task;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void cancel() {
		if (!this.cancelled) {
			this.cancelled = true;
			this.task.onCancel();
		}
	}
}
