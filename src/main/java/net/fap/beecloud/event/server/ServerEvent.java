package net.fap.beecloud.event.server;

import lombok.Getter;
import net.fap.beecloud.event.Event;
import net.fap.beecloud.network.mcpe.protocol.DataPacket;

public class ServerEvent extends Event {
	@Getter
	public DataPacket packet;
}
