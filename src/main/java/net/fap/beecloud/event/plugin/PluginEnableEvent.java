package net.fap.beecloud.event.plugin;

import net.fap.beecloud.plugin.PluginBase;

public class PluginEnableEvent extends PluginEvent {

    public PluginEnableEvent(PluginBase plugin) {
        this.plugin = plugin;
    }

}
