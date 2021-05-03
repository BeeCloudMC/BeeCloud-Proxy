package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.Client;
import net.fap.beecloud.Server;

/**
 * 子服务器尝试连接数据包
 *
 * @author catrainbow
 */

public class ConnectPacket extends BeeCloudPacket {

	private String serverName;
	private String password;
	private String port;
	private Client client;
	private String isLobbyServer;
	private String transferOnShutdown;

	public ConnectPacket(String serverName, String password, String serverPort, String isLobbyServer, String transferOnShutdown) {
		this.password = password;
		if (this.password.equals(Server.getServer().clientPassword) && !Client.allClientServer.containsKey(serverName)) {
			this.serverName = serverName;
			this.port = serverPort;
			this.isLobbyServer = isLobbyServer;
			this.transferOnShutdown = transferOnShutdown;
			this.client = new Client(this.serverName, this.password, this.port, this.isLobbyServer, this.transferOnShutdown);
			Client.registerClients(client);
		}
	}

	public boolean isLobbyServer() {
		return Boolean.valueOf(this.isLobbyServer);
	}

	public Client getClient() {
		return this.client;
	}

	public String getServerName() {
		return serverName;
	}

	public String getPort() {
		return port;
	}

	@Override
	public void putString(String[] pk2) {
		this.serverName = pk2[2];
		this.password = pk2[1];
		this.port = pk2[3];
		this.isLobbyServer = pk2[4];
		this.transferOnShutdown = pk2[5];
	}

	@Override
	public void resend() {
	}

	@Override
	public String to_String() {
		if (this.password.equals(Server.getServer().clientPassword) && !Client.allClientServer.containsKey(serverName))
			return "ConnectPacket:SUCCESS";
		else
			return "ConnectPacket:FAILED";
	}

}
