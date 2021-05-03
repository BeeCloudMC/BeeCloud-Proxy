package net.fap.beecloud.network.mcpe.protocol;

import lombok.Getter;
import lombok.Setter;
import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.network.mcpe.NetworkSessionAdapter;

/**
 * 玩家退出数据包
 * 当玩家退出服务器时会发送这个数据包
 *
 * @author catrainbow
 */

public class DisconnectPacket extends DataPacket {
	@Getter
	@Setter
	public String playerName;

	@Override
	public void handle(NetworkSessionAdapter adapter) {
		adapter.handleDisconnect(this);
	}

	@Override
	public void encodePayload() {
		this.putString(this.getPlayerName());
	}

	@Override
	public void decodeHeader() {
		this.setPlayerName(this.getString());
	}

	@Override
	public byte PID() {
		return ProtocolInfo.DISCONNECT;
	}
}
