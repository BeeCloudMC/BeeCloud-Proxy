package net.fap.beecloud.console.simple;

import net.fap.beecloud.console.ServerLogger;

public class HelpCommand extends CommandHandler {

	public String commandStr = "help";
	public String commandUsage = "默认帮助命令";

	public HelpCommand() {
		setCommandStr(this.commandStr, this.commandUsage);
	}

	@Override
	public void setCommandStr(String commandStr, String commandUsage) {
		super.setCommandStr(commandStr, commandUsage);
	}

	@Override
	public void runCommand(String[] args, String commandSender) {
		if (args.length == 1) {
			ServerLogger.info("--- Showing help page 1 of 1 (/help) ---");
			for (String str : CommandHandler.commandMap.keySet())
				ServerLogger.info(str + " - " + CommandHandler.commandMap.get(str).commandUsage);
		} else if (args.length == 2) {
			if (CommandHandler.commandMap.get(args[1]).commandStr.equals(args[1]))
				ServerLogger.info("Command: " + args[1] + " | Usage: " + CommandHandler.commandMap.get(args[1]).commandUsage);
			else ServerLogger.info("Usage: /help or /help <command>");
		}
		super.runCommand(args, commandSender);
	}
}
