package net.fap.beecloud.console.simple;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.plugin.PluginBase;

public class PluginListCommand extends CommandHandler {

    public String commandStr = "plugin";
    public String commandUsage = "查看安装的插件";

    public PluginListCommand()
    {
        this.setCommandStr(this.commandStr,this.commandUsage);
    }

    @Override
    public void setCommandStr(String commandStr, String commandUsage) {
        super.setCommandStr(commandStr, commandUsage);
    }

    @Override
    public void runCommand() {
        String plugin = " ";
        for (String str : PluginBase.pluginMap)
            plugin+=str+" ";
        ServerLogger.info("Plugins("+ PluginBase.pluginMap.size()+") "+plugin);
    }

}
