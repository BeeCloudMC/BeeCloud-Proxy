package net.fap.beecloud.network.mcpe.protocol;

import lombok.Getter;
import lombok.Setter;
import net.fap.beecloud.network.mcpe.NetworkSessionAdapter;

/**
 * 服务器聊天数据包
 * 当玩家聊天时会触发这个数据包
 *
 * @author catrainbow
 */

public class TextPacket extends DataPacket {
	public static final byte TYPE_PLAYER = 0x01;
	public static final byte TYPE_SERVER = 0x01;
	@Getter
	@Setter
	public byte type;
	@Getter
	@Setter
	public String playerName;
	@Getter
	@Setter
	public String message;

	@Override
	public void handle(NetworkSessionAdapter adapter) {

	}

	@Override
	public void encodePayload() {
		this.putByte(this.getType());
		if (this.getType() == TextPacket.TYPE_PLAYER) {
			this.putString(this.getPlayerName());
		}
		this.putString(this.getMessage());
	}

	@Override
	public void decodePayload() {
		this.setType(this.getByte());
		if (this.getType() == TextPacket.TYPE_PLAYER) {
			this.setPlayerName(this.getString());
		}
		this.setMessage(this.getString());
	}

	@Override
	public byte PID() {
		return ProtocolInfo.TEXT;
	}
}
