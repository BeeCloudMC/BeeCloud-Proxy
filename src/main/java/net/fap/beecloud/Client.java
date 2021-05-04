package net.fap.beecloud;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.console.simple.CommandHandler;
import net.fap.beecloud.event.synapse.ClientConnectEvent;
import net.fap.beecloud.network.mcpe.protocol.CommandRegisterPacket;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 子服务器类
 *
 * @author catrainbow
 */

public class Client {

    public static HashMap<String, Client> allClientServer = new HashMap<>();

    private String serverName;
    private String serverPort;
    private boolean isLobbyServer;
    private boolean transferOnShutdown;
    private String serverPassword;

    private int online;
    private ArrayList<SynapsePlayer> playerList = new ArrayList<>();

    public static void registerClients(Client client) {
        ClientConnectEvent event = new ClientConnectEvent(client);
        event.call();
        if (!event.isCancelled()) {
            allClientServer.put(client.getServerName(), client);
            ServerLogger.info("Client server connect proxy successfully!");
            ServerLogger.info("ServerName: " + client.getServerName() + " Port: " + client.getServerPort());
            ServerLogger.info("LobbyServer: " + client.isLobbyServer + " TransferOnShutdown: " + client.transferOnShutdown);
            for (CommandRegisterPacket pk : CommandHandler.customCommandPacketList) ;
            //Server.getServer().send(pk);
        }
    }

    public static Client getClient(String serverName) {
        return allClientServer.get(serverName);
    }

    public Client(String serverName, String serverPassword, String serverPort, String isLobbyServer, String transferOnShutdown) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.serverPassword = serverPassword;
        this.isLobbyServer = Boolean.valueOf(isLobbyServer);
        this.transferOnShutdown = Boolean.valueOf(transferOnShutdown);
        this.online = 0;
    }

    public void addPlayer(SynapsePlayer player) {
        this.online++;
        this.playerList.add(player);
    }

    public void removePlayer(SynapsePlayer player) {
        this.online--;
        this.playerList.remove(player);
    }

    public boolean isLobbyServer() {
        return this.isLobbyServer;
    }

    public boolean isTransferOnShutdown() {
        return this.transferOnShutdown;
    }

    public int getOnline() {
        return this.online;
    }

    public ArrayList<SynapsePlayer> getPlayerList() {
        return this.playerList;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerPassword(String serverPassword) {
        this.serverPassword = serverPassword;
    }

}
