package net.fap.beecloud.utils;

@SuppressWarnings("unchecked")
public final class BufferedChannel<T> {
	private final Object[] data;
	private final int maxOffset;
	private volatile int offset;

	public BufferedChannel(int capacity) {
		this.maxOffset = capacity - 1;
		this.data = new Object[capacity];
		this.offset = 0;
	}

	public int getOffset() {
		return offset;
	}

	public synchronized void send(T val) throws InterruptedException {
		if (this.offset + 1 > this.maxOffset) {
			this.wait();
		}
		this.data[this.offset++] = val;
	}

	public synchronized T receive() {
		T ret = (T) this.data[this.offset--];
		this.notify();
		return ret;
	}
}
