package net.fap.beecloud;

public class BeeCloud {

    public static Server server;

    public static void main(String[] args) {
        server = new Server();
        server.init();
    }

}
