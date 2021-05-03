package net.fap.beecloud.plugin;

import net.fap.beecloud.Server;
import net.fap.beecloud.event.Event;
import net.fap.beecloud.event.Listener;

public class RegisterListener {

	private final Listener listener;
	private final PluginBase plugin;

	public RegisterListener(PluginBase plugin, Listener listener) {
		this.plugin = plugin;
		this.listener = listener;
		Server.getInstance().serverListeners.add(this);
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
