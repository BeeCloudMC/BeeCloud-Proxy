package net.fap.beecloud.plugin;

import net.fap.beecloud.Server;
import net.fap.beecloud.event.Event;
import net.fap.beecloud.event.Listener;

public class RegisterListener {

    private Listener listener;
    private PluginBase plugin;

    public RegisterListener(PluginBase plugin, Listener listener) {
        this.plugin = plugin;
        this.listener = listener;
        Server.getServer().serverListeners.add(this);
    }

    public Listener getListener() {
        return this.listener;
    }

    public PluginBase getPlugin() {
        return plugin;
    }

    public void callEvent(Event event) {
        listener.call(event);
    }

}
