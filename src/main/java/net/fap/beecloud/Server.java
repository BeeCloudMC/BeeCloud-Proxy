package net.fap.beecloud;

import cn.hutool.core.io.FileUtil;
import lombok.Getter;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.console.simple.*;
import net.fap.beecloud.event.BeeCloudListener;
import net.fap.beecloud.event.EventHandler;
import net.fap.beecloud.event.Listener;
import net.fap.beecloud.event.server.DataPacketSendEvent;
import net.fap.beecloud.math.BeeCloudMath;
import net.fap.beecloud.network.mcpe.protocol.CommandRegisterPacket;
import net.fap.beecloud.network.mcpe.protocol.DataPacket;
import net.fap.beecloud.plugin.PluginBase;
import net.fap.beecloud.plugin.PluginLoader;
import net.fap.beecloud.plugin.RegisterListener;
import net.fap.beecloud.utils.Shutdown;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * FillAmraPixel Project
 *
 * @author catrainbow
 */

public class Server {
	@Getter
	private static Server instance;
	@Getter
	private final String workingPath;
	@Getter
	private final ServerConfigGroup configGroup;
	@Getter
	public ArrayList<SynapsePlayer> onlinePlayers = new ArrayList<>();

	public Server(String workingPath) {
		this.workingPath = workingPath;
		if (Server.instance != null) {
			throw new RuntimeException("Server is initialized");
		}
		File pluginPath = new File(workingPath + "/plugins");
		FileUtil.mkdir(pluginPath);
		final File cfg = FileUtil.file(workingPath, "server.json");
		FileUtil.touch(cfg);
		FileUtil.writeFromStream(this.getClass().getClassLoader().getResourceAsStream("server.json"), cfg);
		this.configGroup = new ServerConfigGroup(cfg);
	}

	public void start() {
		ServerLogger.info("-- BeeCloud Proxy --");
		instance = this;
		ServerLogger.waring("- Running your server on: " + this.getConfigGroup().getPort() + " -");
		CommandHandler.registerCommand(new HelpCommand());
		CommandHandler.registerCommand(new ListCommand());
		CommandHandler.registerCommand(new VersionCommand());
		CommandHandler.registerCommand(new PluginListCommand());
		CommandHandler.registerCommand(new StopCommand());
		EventHandler.setListener(new BeeCloudListener());

		ServerLogger.info("- Enabling plugins... -");
		PluginLoader.loadPlugin(this);

		Shutdown.shutdownTask();

		ServerLogger.info("- Done! For help type /help");

		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						titleTick();
						receive();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
							String commandStr = null;
							while ((commandStr = br.readLine()) != null)
								CommandHandler.handleCommand(commandStr, "CONSOLE");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void registerListeners(PluginBase plugin, Listener listener) {
		RegisterListener registerListener = new RegisterListener(plugin, listener);
	}

	public void reloadListener(Listener listener) {
		EventHandler.setListener(listener);
	}

	private void receive() throws IOException {
		DatagramSocket ds = new DatagramSocket(this.getConfigGroup().getPort());
		while (true) {
			byte[] bytes = new byte[1024];
			DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
			ds.receive(dp);
			String pk1 = new String(dp.getData(), 0, dp.getLength());
			//String pk2 = new String(pk1.getBytes(ENCODING_GBK), ENCODING_GBK);
			//Packet.handlePacket(pk1);
		}
	}

	public String getName() {
		return "BeeCloud";
	}

	public void send(DataPacket pk) {
		try {
			DataPacketSendEvent event = new DataPacketSendEvent(pk);
			event.call();
			if (!event.isCancelled()) {
				DatagramSocket ds = new DatagramSocket();
				pk.encode();
				InetAddress address = InetAddress.getByName("127.0.0.1");
				DatagramPacket dp = new DatagramPacket(pk.getBuffer(), pk.getBuffer().length, address, this.getConfigGroup().getProxiedPort());
				ds.send(dp);
				ds.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registerCommand(CommandHandler commandHandler) {
		CommandHandler.registerCommand(commandHandler);
	}

	public void registerNukkitCommand(CommandHandler commandHandler) {
		CommandHandler.registerCommand(commandHandler);
		CommandRegisterPacket pk = new CommandRegisterPacket(commandHandler.commandStr, commandHandler.commandUsage);
		CommandHandler.customCommandPacketList.add(pk);
		send(pk);
	}

	public File getPluginFile() {
		return FileUtil.file(this.getWorkingPath(), "plugins");
	}

	private void titleTick() {
		Runtime runtime = Runtime.getRuntime();
		double used = BeeCloudMath.round((double) (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024, 2);
		double max = BeeCloudMath.round((double) runtime.maxMemory() / 1024.0D / 1024.0D, 2);
		String usage = Math.round(used / max * 100.0D) + "%";
		String title = this.getName() + " - Memory:" + used + "/" + max + "(" + usage + ")";
	}
}
