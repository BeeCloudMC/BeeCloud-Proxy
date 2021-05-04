package net.fap.beecloud.event.player;

import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.network.mcpe.protocol.LoginPacket;

public class PlayerJoinEvent extends PlayerEvent {

	protected String joinMessage;

	public PlayerJoinEvent(LoginPacket packet) {
		this.player = packet.getInfo().getName();
	}

	public PlayerJoinEvent(SynapsePlayer player) {
		this.player = player.player;
	}

	public void setJoinMessage(String str) {
		this.joinMessage = str;
	}

}
