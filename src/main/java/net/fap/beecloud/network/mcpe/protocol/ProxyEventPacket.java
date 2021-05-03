package net.fap.beecloud.network.mcpe.protocol;

import lombok.Getter;
import lombok.Setter;
import net.fap.beecloud.network.mcpe.NetworkSessionAdapter;

/**
 * BeeCloud关闭数据包
 * 当BeeCloud Proxy关闭时会发送这个包
 *
 * @author catrainbow
 */

public class ProxyEventPacket extends DataPacket {
	public static final byte EVENT_SHUTDOWN = 0x01;
	@Getter
	@Setter
	public byte event;

	@Override
	public void encodePayload() {
		this.putByte(this.getEvent());
	}

	@Override
	public void decodePayload() {
		this.setEvent(this.getByte());
	}

	@Override
	public void handle(NetworkSessionAdapter adapter) {

	}

	@Override
	public byte PID() {
		return ProtocolInfo.PROXY_EVENT;
	}
}
