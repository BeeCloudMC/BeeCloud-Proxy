package net.fap.beecloud.event.server;

import net.fap.beecloud.network.mcpe.protocol.DataPacket;

public class DataPacketSendEvent extends ServerEvent {

	public DataPacketSendEvent(DataPacket packet) {
		this.packet = packet;
	}

}
