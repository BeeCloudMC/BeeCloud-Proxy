package net.fap.beecloud;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.event.player.PlayerJoinEvent;
import net.fap.beecloud.event.player.PlayerQuitEvent;
import net.fap.beecloud.network.mcpe.protocol.LoginPacket;
import net.fap.beecloud.network.mcpe.protocol.QuitPacket;
import net.fap.beecloud.network.mcpe.protocol.TransferPacket;
import net.fap.beecloud.network.mcpe.protocol.custom.CustomPacket;

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
		Server.getInstance().getOnlinePlayers().add(new SynapsePlayer(packet.getPlayer(), packet.address, packet.uuid, packet.clientID, packet.serverName));
		if (!event.isCancelled()) {
			ServerLogger.info(packet.getPlayer() + "[" + packet.address + "] joined the game.");
			Client.getClient(packet.serverName).addPlayer(SynapsePlayer.getPlayer(packet.getPlayer()));
		} else getPlayer(packet.getPlayer()).kick("§cLogin out of the synapse server");
	}

	public static void removePlayer(QuitPacket packet) {
		Client.getClient(SynapsePlayer.getPlayer(packet.getPlayer()).serverName).removePlayer(SynapsePlayer.getPlayer(packet.getPlayer()));
		Server.getInstance().getOnlinePlayers().remove(getPlayer(packet.getPlayer()));
		ServerLogger.info(packet.getPlayer() + " quited the game.");
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

	public void sendMessage(String message) {
		CustomPacket packet = new CustomPacket();
		packet.putString(new String[]{"TextMessagePacket", this.getName(), message});
		Server.getInstance().send(packet);
	}

	public String getServerName() {
		return this.serverName;
	}

	public void sendTitle(String main, String sub, int fadein, int stay, int fadeout) {
		CustomPacket packet = new CustomPacket();
		packet.putString(new String[]{"TextTitlePacket", this.getName(), main + ":" + sub + ":" + fadein + ":" + stay + ":" + fadeout});
		Server.getInstance().send(packet);
	}

	public void sendTitle(String main, String sub) {
		CustomPacket packet = new CustomPacket();
		packet.putString(new String[]{"TextTitlePacket", this.getName(), main + ":" + sub});
		Server.getInstance().send(packet);
	}

	public void sendTip(String message) {
		CustomPacket packet = new CustomPacket();
		packet.putString(new String[]{"TextTipPacket", this.getName(), message});
		Server.getInstance().send(packet);
	}

	public void kick(String reason) {
		CustomPacket packet = new CustomPacket();
		packet.putString(new String[]{"KickPlayerPacket", this.getName(), reason});
		Server.getInstance().send(packet);
	}

	public void kick() {
		CustomPacket packet = new CustomPacket();
		packet.putString(new String[]{"KickPlayerPacket", this.getName(), "Kicked by admin"});
		Server.getInstance().send(packet);
	}

	public void sendPacket(String packetName, String packetV) {
		CustomPacket packet = new CustomPacket();
		packet.putString(new String[]{packetName, this.getName(), packetV});
	}

	public void transferPlayer(Client client) {
		TransferPacket packet = new TransferPacket(this, client);
		Server.getInstance().send(packet);
	}
}
