package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.SynapsePlayer;

/**
 * 玩家退出数据包
 * 当玩家退出服务器时会发送这个数据包
 *
 * @author catrainbow
 */

public class QuitPacket extends BeeCloudPacket {

	public String player;

	public String getPlayer() {
		return this.player;
	}

	@Override
	public void putString(String[] pk2) {
		this.player = pk2[1];
	}

	@Override
	public void resend() {
		SynapsePlayer.removePlayer(this);
	}

	@Override
	public String to_String() {
		return "QuitPacket:" + player;
	}
}
