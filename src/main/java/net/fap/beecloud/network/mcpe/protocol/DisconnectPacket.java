package net.fap.beecloud.network.mcpe.protocol;

import lombok.Getter;
import lombok.Setter;
import net.fap.beecloud.network.mcpe.NetworkSessionAdapter;
import net.fap.beecloud.network.mcpe.protocol.types.PlayerIdentityInfo;

/**
 * 玩家退出数据包
 * 当玩家退出服务器时会发送这个数据包
 *
 * @author catrainbow
 */

public class DisconnectPacket extends DataPacket {
	@Getter
	@Setter
	public PlayerIdentityInfo info;
	@Getter
	@Setter
	public String reason;

	@Override
	public void handle(NetworkSessionAdapter adapter) {
		adapter.handleDisconnect(this);
	}

	@Override
	public void encodePayload() {
		this.info.encode(this);
		this.putString(this.reason);
	}

	@Override
	public void decodeHeader() {
		this.info = new PlayerIdentityInfo();
		this.info.decode(this);
		this.putString(this.reason);
	}

	@Override
	public byte PID() {
		return ProtocolInfo.DISCONNECT;
	}
}
