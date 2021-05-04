package net.fap.beecloud.event;

public class EventHandler {

    private static Listener listener;

    public static void setListener(Listener lis) {
        listener = lis;
    }

    public static Listener getListener() {
        return listener;
    }

    public static void callEvent(Event event) {
        listener.call(event);
    }

}
