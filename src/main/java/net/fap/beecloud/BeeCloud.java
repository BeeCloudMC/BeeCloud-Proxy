package net.fap.beecloud;

public class BeeCloud {
	private static final String serverPath = String.valueOf(System.getProperty("user.dir"));

	public static String getServerPath() {
		return serverPath;
	}

	public static void main(String[] args) {
		new Server(getServerPath()).start();
	}
}
