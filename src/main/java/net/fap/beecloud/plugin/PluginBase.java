package net.fap.beecloud.plugin;

import net.fap.beecloud.Server;

import java.util.ArrayList;

public class PluginBase {

	public static ArrayList<PluginBase> pluginList = new ArrayList<>();

	private String name;

	private String jar;

	private String className;

	private PluginLogger pluginLogger;

	public Server getServer() {
		return Server.getInstance();
	}

	public PluginLogger getLogger() {
		return this.pluginLogger;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setJar(String jar) {
		this.jar = jar;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPluginLogger(PluginLogger logger) {
		this.pluginLogger = logger;
	}

	public String getJar() {
		return jar;
	}

	public String getClassName() {
		return className;
	}

	public String getName() {
		return name;
	}

}
