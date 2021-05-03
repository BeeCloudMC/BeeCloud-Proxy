package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.Client;
import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.console.ServerLogger;

/**
 * 玩家传送数据包
 * 当一个玩家试图从一个子服务器传送到另一个子服务器时会触发
 *
 * @author catrainbow
 */

public class TransferPacket extends BeeCloudPacket {

	private final String player;
	private final String serverName;
	private final String port;

	public TransferPacket(SynapsePlayer player, Client target) {
		this.player = player.getName();
		this.serverName = target.getServerName();
		this.port = target.getServerPort();
	}

	public String getServerName() {
		return serverName;
	}

	public String getPlayer() {
		return player;
	}

	@Override
	public void resend() {
		ServerLogger.info(player + " has transfer to " + serverName);
		super.resend();
	}

	@Override
	public String to_String() {
		return "TransferPacket:" + serverName + ":" + player + ":" + port;
	}
}
