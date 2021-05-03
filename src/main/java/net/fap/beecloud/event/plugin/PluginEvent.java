package net.fap.beecloud.event.plugin;

import net.fap.beecloud.event.Event;
import net.fap.beecloud.plugin.PluginBase;

public class PluginEvent extends Event {

	public PluginBase plugin;

	public PluginBase getPlugin() {
		return plugin;
	}

	public String getPluginName() {
		return plugin.getName();
	}

}
