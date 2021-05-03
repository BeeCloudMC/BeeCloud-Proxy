package net.fap.beecloud.network.mcpe.protocol;

/**
 * BeeCloud关闭数据包
 * 当BeeCloud Proxy关闭时会发送这个包
 *
 * @author catrainbow
 */

public class DisconnectPacket extends BeeCloudPacket {

	@Override
	public void putString(String[] pk2) {

	}

	@Override
	public void resend() {

	}

	@Override
	public String to_String() {
		return "DisconnectPacket";
	}
}
