package net.fap.beecloud;

import lombok.Getter;
import net.fap.beecloud.utils.JsonConfig;

import java.io.File;

public class ServerConfigGroup {
	@Getter
	private final JsonConfig config;

	public ServerConfigGroup(File file) {
		this.config = new JsonConfig(file);
	}

	public int getPort() {
		return this.config.getInteger("server-port");
	}

	public int getProxiedPort() {
		return this.getPort() + 1;
	}

	public String getSynapsePassword() {
		return this.config.getString("synapse-password");
	}
}
