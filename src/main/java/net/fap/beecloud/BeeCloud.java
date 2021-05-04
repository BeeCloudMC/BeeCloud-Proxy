package net.fap.beecloud;

public class BeeCloud {
	public static void main(String[] args) throws InterruptedException {
		new Server(System.getProperty("user.dir")).start();
	}
}
