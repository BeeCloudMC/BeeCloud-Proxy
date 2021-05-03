package net.fap.beecloud.event.player;

import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.network.mcpe.protocol.QuitPacket;

public class PlayerQuitEvent extends PlayerEvent {

	public PlayerQuitEvent(SynapsePlayer player) {
		this.player = player.player;
	}

	public PlayerQuitEvent(QuitPacket packet) {
		this.player = packet.getPlayer();
	}

}
