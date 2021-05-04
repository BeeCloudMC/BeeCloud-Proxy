package net.fap.beecloud;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.event.player.PlayerJoinEvent;
import net.fap.beecloud.event.player.PlayerQuitEvent;
import net.fap.beecloud.inventory.PlayerInventory;
import net.fap.beecloud.network.mcpe.protocol.LoginPacket;
import net.fap.beecloud.network.mcpe.protocol.QuitPacket;
import net.fap.beecloud.network.mcpe.protocol.TransferPacket;
import net.fap.beecloud.network.mcpe.protocol.custom.CustomPacket;
import net.fap.beecloud.permission.Permission;

/**
 * FillAmeaPixel Project
 * BeeCloud Server
 *
 * @author catrainbow
 */

public class SynapsePlayer {

    public static boolean savePlayerData = false;

    public String clientUUid;
    public String clientID;
    public String player;
    public String address;
    ;
    public String serverName;
    public String defaultPermission;

    private Permission permission;
    private PlayerInventory inventory;

    public SynapsePlayer(String player, String address, String clientUUId, String clientID, String serverName, String permission) {
        this.clientUUid = clientUUId;
        this.clientID = clientID;
        this.player = player;
        this.address = address;
        this.serverName = serverName;
        this.defaultPermission = permission;
        this.permission = new Permission(this);
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
        //Server.getServer().send(packet);
    }

    public String getServerName() {
        return this.serverName;
    }

    public void sendTitle(String main, String sub, int fadein, int stay, int fadeout) {
        CustomPacket packet = new CustomPacket();
        packet.putString(new String[]{"TextTitlePacket", this.getName(), main + ":" + sub + ":" + fadein + ":" + stay + ":" + fadeout});
        //Server.getServer().send(packet);
    }

    public void sendTitle(String main, String sub) {
        CustomPacket packet = new CustomPacket();
        packet.putString(new String[]{"TextTitlePacket", this.getName(), main + ":" + sub});
        //Server.getServer().send(packet);
    }

    public void sendTip(String message) {
        CustomPacket packet = new CustomPacket();
        packet.putString(new String[]{"TextTipPacket", this.getName(), message});
        //Server.getServer().send(packet);
    }

    public void kick(String reason) {
        CustomPacket packet = new CustomPacket();
        packet.putString(new String[]{"KickPlayerPacket", this.getName(), reason});
        //Server.getServer().send(packet);
    }

    public void kick() {
        CustomPacket packet = new CustomPacket();
        packet.putString(new String[]{"KickPlayerPacket", this.getName(), "Kicked by admin"});
        //Server.getServer().send(packet);
    }

    public void sendPacket(String packetName, String packetV) {
        CustomPacket packet = new CustomPacket();
        packet.putString(new String[]{packetName, this.getName(), packetV});
    }

    public static void addPlayer(LoginPacket packet) {
        PlayerJoinEvent event = new PlayerJoinEvent(packet);
        event.call();
        Server.onLinePlayerList.add(new SynapsePlayer(packet.getPlayer(), packet.address, packet.uuid, packet.clientID, packet.serverName, packet.permission));
        if (!event.isCancelled()) {
            ServerLogger.info(packet.getPlayer() + "[" + packet.address + "] joined the game.");
            Client.getClient(packet.serverName).addPlayer(SynapsePlayer.getPlayer(packet.getPlayer()));
            SynapsePlayer.getPlayer(packet.getPlayer()).inventory = new PlayerInventory(SynapsePlayer.getPlayer(packet.getPlayer()));
        } else getPlayer(packet.getPlayer()).kick("§cLogin out of the synapse server");
    }

    public static void removePlayer(QuitPacket packet) {
        Client.getClient(SynapsePlayer.getPlayer(packet.getPlayer()).serverName).removePlayer(SynapsePlayer.getPlayer(packet.getPlayer()));
        Server.onLinePlayerList.remove(getPlayer(packet.getPlayer()));
        ServerLogger.info(packet.getPlayer() + " quited the game.");
        PlayerQuitEvent event = new PlayerQuitEvent(packet);
        event.call();
    }

    public static SynapsePlayer getPlayer(String player) {
        for (int i = 0; i < Server.onLinePlayerList.size(); i++)
            if (Server.onLinePlayerList.get(i).player.equals(player))
                return Server.onLinePlayerList.get(i);
        return null;
    }

    public void transferPlayer(Client client) {
        TransferPacket packet = new TransferPacket(this, client);
        //Server.getServer().send(packet);
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void addPermission(String permission) {
        this.permission.permissionList.add(permission);
    }

    public boolean hasPermission(String permission) {
        return this.permission.permissionList.contains(permission);
    }

    public boolean isPlayer() {
        return true;
    }

    public boolean isOp() {
        return this.defaultPermission.equals(Permission.DEFAULT_OP);
    }

    public PlayerInventory getInventory() {
        return this.inventory;
    }

}
