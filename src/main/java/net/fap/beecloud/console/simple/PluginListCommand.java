package net.fap.beecloud.console.simple;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.plugin.PluginBase;
import org.fusesource.jansi.Ansi;

public class PluginListCommand extends CommandHandler {

	public String commandStr = "plugin";
	public String commandUsage = "查看安装的插件";

	public PluginListCommand() {
		this.setCommandStr(this.commandStr, this.commandUsage);
	}

	@Override
	public void setCommandStr(String commandStr, String commandUsage) {
		super.setCommandStr(commandStr, commandUsage);
	}

	@Override
	public void runCommand(String[] args, String commandSender) {
		if (args.length == 1) {
			StringBuilder plugin = new StringBuilder(" ");
			for (PluginBase pluginBase : PluginBase.pluginList)
				plugin.append(pluginBase.getName()).append(" ");
			ServerLogger.info("Plugins(" + PluginBase.pluginList.size() + ") " + Ansi.ansi().fg(Ansi.Color.GREEN).a(plugin.toString()).reset());
		} else ServerLogger.info("Usage: " + commandUsage);
	}

}
