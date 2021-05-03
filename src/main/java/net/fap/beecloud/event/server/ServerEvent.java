package net.fap.beecloud.event.server;

import net.fap.beecloud.event.Event;
import net.fap.beecloud.network.mcpe.protocol.BeeCloudPacket;

public class ServerEvent extends Event {

	public BeeCloudPacket packet;

	public BeeCloudPacket getPacket() {
		return packet;
	}

}
