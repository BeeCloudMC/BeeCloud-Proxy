package net.fap.beecloud.network.mcpe;

import net.fap.beecloud.network.mcpe.protocol.DataPacket;
import net.fap.beecloud.network.mcpe.protocol.LoginPacket;
import net.fap.beecloud.network.mcpe.protocol.UnknownPacket;

import java.util.LinkedHashMap;
import java.util.Map;

public class PacketPool {
	private static final Map<Byte, DataPacket> pool = new LinkedHashMap<>(Byte.MAX_VALUE);

	public static void init() {
		PacketPool.register(new LoginPacket());
	}

	public static void register(DataPacket packet) {
		PacketPool.pool.put(packet.PID(), packet.clone());
	}

	public static DataPacket getPacket(byte id, byte[] payload) {
		DataPacket pk = PacketPool.pool.getOrDefault(id, new UnknownPacket()).clone();
		pk.setBuffer(payload);
		return pk;
	}
}
