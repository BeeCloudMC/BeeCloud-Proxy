package net.fap.beecloud.event.server;

import net.fap.beecloud.network.mcpe.protocol.DataPacket;

public class DataPacketReceiveEvent extends ServerEvent {

	public DataPacketReceiveEvent(DataPacket packet) {
		this.packet = packet;
	}

}
