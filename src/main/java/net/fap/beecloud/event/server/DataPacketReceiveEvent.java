package net.fap.beecloud.event.server;

public class DataPacketReceiveEvent extends ServerEvent {

	public DataPacketReceiveEvent(BeeCloudPacket packet) {
		this.packet = packet;
	}

}
