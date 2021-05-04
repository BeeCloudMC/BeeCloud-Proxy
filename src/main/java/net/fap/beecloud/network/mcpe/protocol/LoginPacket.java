package net.fap.beecloud.network.mcpe.protocol;

import lombok.Getter;
import lombok.Setter;
import net.fap.beecloud.network.mcpe.NetworkSessionAdapter;
import net.fap.beecloud.network.mcpe.protocol.types.PlayerIdentityInfo;

/**
 * 玩家登录数据包
 * 当一个新的玩家加入服务器时会创建这个数据包
 *
 * @author catrainbow
 */

public class LoginPacket extends DataPacket {
	@Getter
	@Setter
	public PlayerIdentityInfo info;
	@Setter
	@Getter
	public String server;

	@Override
	public void handle(NetworkSessionAdapter adapter) {
		adapter.handleLogin(this);
	}

	@Override
	public void encodePayload() {
		this.info.encode(this);
		this.putString(this.getServer());
	}

	@Override
	public void decodePayload() {
		this.info = new PlayerIdentityInfo();
		this.info.decode(this);
		this.setServer(this.getString());
	}

	@Override
	public byte PID() {
		return ProtocolInfo.LOGIN;
	}
}
