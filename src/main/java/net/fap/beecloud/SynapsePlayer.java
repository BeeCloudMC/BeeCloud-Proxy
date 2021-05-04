package net.fap.beecloud;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.event.player.PlayerJoinEvent;
import net.fap.beecloud.event.player.PlayerQuitEvent;
import net.fap.beecloud.network.mcpe.protocol.DisconnectPacket;
import net.fap.beecloud.network.mcpe.protocol.LoginPacket;

/**
 * FillAmeaPixel Project
 * BeeCloud Server
 *
 * @author catrainbow
 */

public class SynapsePlayer {
	public String clientUUid;
	public String clientID;
	public String player;
	public String address;
	public String serverName;

	public SynapsePlayer(String player, String address, String clientUUId, String clientID, String serverName) {
		this.clientUUid = clientUUId;
		this.clientID = clientID;
		this.player = player;
		this.address = address;
		this.serverName = serverName;
	}

	public static void addPlayer(LoginPacket packet) {
		PlayerJoinEvent event = new PlayerJoinEvent(packet);
		event.call();
		Server.getInstance().getOnlinePlayers().add(new SynapsePlayer(packet.getInfo().getName(), packet.getInfo().getAddr(), packet.getInfo().getUUID(), packet.getInfo().getUUID(), packet.server));
		if (!event.isCancelled()) {
			ServerLogger.info(packet.getInfo().getName() + "[" + packet.getInfo().getAddr() + "] joined the game.");
			Client.getClient(packet.server).addPlayer(SynapsePlayer.getPlayer(packet.getInfo().getName()));
		} else {
			//getPlayer(packet.getInfo().getName()).kick("§cLogin out of the synapse server");
		}
	}

	public static void removePlayer(DisconnectPacket packet) {
		Client.getClient(SynapsePlayer.getPlayer(packet.getInfo().getName()).serverName).removePlayer(SynapsePlayer.getPlayer(packet.getInfo().getName()));
		Server.getInstance().getOnlinePlayers().remove(getPlayer(packet.getInfo().getName()));
		ServerLogger.info(packet.getInfo().getName() + " quited the game.");
		PlayerQuitEvent event = new PlayerQuitEvent(packet);
		event.call();
	}

	public static SynapsePlayer getPlayer(String player) {
		for (int i = 0; i < Server.getInstance().getOnlinePlayers().size(); i++) {
			if (Server.getInstance().getOnlinePlayers().get(i).player.equals(player)) {
				return Server.getInstance().getOnlinePlayers().get(i);
			}
		}
		return null;
	}

	public String getName() {
		return this.player;
	}

	public String getAddress() {
		return this.address;
	}

	public String getClientID() {
		return clientID;
	}

	public String getClientUUid() {
		return clientUUid;
	}

	public String getServerName() {
		return this.serverName;
	}

}
