package net.fap.beecloud.console.simple;

import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.network.mcpe.protocol.CommandRegisterPacket;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandHandler {

	public static HashMap<String, CommandHandler> commandMap = new HashMap<>();
	public static ArrayList<CommandRegisterPacket> customCommandPacketList = new ArrayList<>();

	public String commandStr;
	public String commandUsage;

	public static void registerCommand(CommandHandler commandHandler) {
		commandMap.put(commandHandler.commandStr, commandHandler);
	}

	public static void handleCommand(String commandStr, String commandSender) {
		if (commandStr.length() > 0 && commandMap.containsKey(commandStr.split("\\s+")[0])) {
			commandMap.get(commandStr.split("\\s+")[0]).runCommand(commandStr.split("\\s+"), commandSender);
		} else ServerLogger.info("Unknown command! type help for help.");
	}

	public static void handleCommand(String command, String[] commandArgs, String commandSender) {
		if (command.length() > 0 && commandMap.containsKey(command))
			commandMap.get(command).runCommand(commandArgs, commandSender);
		else SynapsePlayer.getPlayer(commandSender).sendMessage("Unknown command! type help for help.");
	}

	public void setCommandStr(String commandStr, String commandUsage) {
		this.commandStr = commandStr;
		this.commandUsage = commandUsage;
	}

	public void runCommand(String[] args, String commandSender) {

	}

}
