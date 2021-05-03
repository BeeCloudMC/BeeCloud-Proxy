package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.SynapsePlayer;

/**
 * 玩家登录数据包
 * 当一个新的玩家加入服务器时会创建这个数据包
 *
 * @author catrainbow
 */

public class LoginPacket extends BeeCloudPacket {

	public String player;

	public String address;

	public String uuid;

	public String clientID;

	public String serverName;

	@Override
	public void putString(String[] pk2) {
		this.player = pk2[1];
		this.address = pk2[2];
		this.uuid = pk2[3];
		this.clientID = pk2[4];
		this.serverName = pk2[5];
	}

	public String getPlayer() {
		return this.player;
	}

	@Override
	public void resend() {
		SynapsePlayer.addPlayer(this);
	}

	@Override
	public String to_String() {
		return "LoginPacket:" + player + ":" + address + ":" + uuid + ":" + clientID + ":" + serverName;
	}
}
