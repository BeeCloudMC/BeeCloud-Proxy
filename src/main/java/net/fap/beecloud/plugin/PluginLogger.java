package net.fap.beecloud.plugin;

import net.fap.beecloud.console.ServerLogger;

public class PluginLogger {

    private PluginBase pluginBase;

    public PluginLogger() {
    }

    public void info(String info) {
        ServerLogger.info(ServerLogger.getTime() + " [" + pluginBase.getName() + "] " + info);
    }

    public void setPluginBase(PluginBase pluginBase) {
        this.pluginBase = pluginBase;
    }

    public PluginBase getPlugin() {
        return pluginBase;
    }
}
