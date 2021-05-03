package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.console.ServerLogger;

/**
 * 服务器聊天数据包
 * 当玩家聊天时会触发这个数据包
 *
 * @author catrainbow
 */

public class ServerChatPacket extends BeeCloudPacket {

	public String message;
	public String player;
	public String server;
	private String messageAll;

	public ServerChatPacket(String player, String server, String message) {
		this.player = player;
		this.server = server;
		this.message = message;
		messageAll = "§8[" + server + "]§r" + player + "§8>>§r" + this.message;
	}

	public String getPlayer() {
		return player;
	}

	public String getMessageAll() {
		return messageAll;
	}

	public String getMessage() {
		return message;
	}

	public void setMessageAll(String message) {
		this.messageAll = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void resend() {
		ServerLogger.info(messageAll);
	}

	@Override
	public void putString(String[] pk2) {
		this.server = pk2[1];
		this.player = pk2[2];
		this.message = pk2[3];
	}

	@Override
	public String to_String() {
		return "ServerChatPacket:" + messageAll;
	}

}
