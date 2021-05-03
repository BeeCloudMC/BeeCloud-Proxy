package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.network.mcpe.NetworkSessionAdapter;

public class UnknownPacket extends DataPacket {
	@Override
	public void handle(NetworkSessionAdapter adapter) {

	}

	@Override
	public byte PID() {
		return -1;
	}
}
