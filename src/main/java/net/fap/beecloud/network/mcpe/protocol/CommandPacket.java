package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.console.simple.CommandHandler;

/**
 * 命令接收数据包
 *
 * @author catrainbow
 */

public class CommandPacket extends BeeCloudPacket {
	private String commandMessage;
	private String commandSender;
	private String[] commandArgs;

	public CommandPacket(String commandMessage, String commandSender, String[] commandArgs) {
		this.commandMessage = commandMessage;
		this.commandSender = commandSender;
		this.commandArgs = commandArgs;
		String str1 = commandMessage.replace("/", "");
		String[] array1 = str1.split("\\s+");
		String message = str1.replace(array1[0], "");
		String[] array2 = message.split("\\:");
		CommandHandler.handleCommand(array1[0], array2, commandSender);
	}

	public SynapsePlayer getCommandSender() {
		return SynapsePlayer.getPlayer(commandSender);
	}

	public String getCommandMessage() {
		return this.commandMessage;
	}

	public String[] getCommandArgs() {
		return this.commandArgs;
	}

	@Override
	public void resend() {
		super.resend();
	}

	@Override
	public void putString(String[] pk2) {
		this.commandSender = pk2[1];
		this.commandMessage = pk2[2];
		this.commandArgs = commandMessage.replace("/", "").split("\\s+");
	}

	@Override
	public String to_String() {
		return "CommandPacket:" + commandSender + ":" + commandMessage;
	}
}
