package net.fap.beecloud.network.mcpe.protocol;

import lombok.Getter;
import lombok.Setter;
import net.fap.beecloud.network.mcpe.NetworkSessionAdapter;

/**
 * 玩家登录数据包
 * 当一个新的玩家加入服务器时会创建这个数据包
 *
 * @author catrainbow
 */

public class LoginPacket extends DataPacket {
	@Setter
	@Getter
	public String address;
	@Setter
	@Getter
	public String playerName;
	@Setter
	@Getter
	public String uuid;
	@Setter
	@Getter
	public String clientID;
	@Setter
	@Getter
	public String server;

	@Override
	public void handle(NetworkSessionAdapter adapter) {
		adapter.handleLogin(this);
	}

	@Override
	public void encodePayload() {
		this.putString(this.getAddress());
		this.putString(this.getPlayerName());
		this.putString(this.getUuid());
		this.putString(this.getClientID());
		this.putString(this.getServer());
	}

	@Override
	public void decodePayload() {
		this.setAddress(this.getString());
		this.setPlayerName(this.getString());
		this.setUuid(this.getString());
		this.setClientID(this.getString());
		this.setServer(this.getString());
	}

	@Override
	public byte PID() {
		return ProtocolInfo.LOGIN;
	}
}
