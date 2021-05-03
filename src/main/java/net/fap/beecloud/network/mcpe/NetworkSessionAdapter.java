package net.fap.beecloud.network.mcpe;

import net.fap.beecloud.network.mcpe.protocol.DataPacket;
import net.fap.beecloud.network.mcpe.protocol.DisconnectPacket;
import net.fap.beecloud.network.mcpe.protocol.LoginPacket;
import net.fap.beecloud.network.mcpe.protocol.TextPacket;

public interface NetworkSessionAdapter {
	void handle(DataPacket packet);

	void handleLogin(LoginPacket packet);
	void handleDisconnect(DisconnectPacket packet);
	void handleText(TextPacket packet);
}
