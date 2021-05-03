package net.fap.beecloud;

public class BeeCloud {
	public static void main(String[] args) {
		new Server(System.getProperty("user.dir")).start();
	}
}
