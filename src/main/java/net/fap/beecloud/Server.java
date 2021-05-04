package net.fap.beecloud;

import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.console.simple.*;
import net.fap.beecloud.event.BeeCloudListener;
import net.fap.beecloud.event.EventHandler;
import net.fap.beecloud.event.Listener;
import net.fap.beecloud.math.BeeCloudMath;
import net.fap.beecloud.network.mcpe.protocol.BeeCloudPacket;
import net.fap.beecloud.network.mcpe.protocol.CommandRegisterPacket;
import net.fap.beecloud.network.netty.NettyServerIniter;
import net.fap.beecloud.plugin.PluginBase;
import net.fap.beecloud.plugin.PluginLoader;
import net.fap.beecloud.plugin.RegisterListener;
import net.fap.beecloud.scheduler.Scheduler;
import net.fap.beecloud.scheduler.TaskManager;
import net.fap.beecloud.utils.Shutdown;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.*;
import java.util.ArrayList;

/**
 * FillAmraPixel Project
 *
 * @author catrainbow
 */

public class Server {
    private static Server server;
    public int port;
    public String clientPassword;
    private String serverPath = String.valueOf(System.getProperty("user.dir"));
    private File config = new File(this.getDataPath() + "/server.properties");
    private File pluginData = new File(this.getDataPath() + "/plugins/");
    public static ArrayList<SynapsePlayer> onLinePlayerList = new ArrayList<>();
    public ArrayList<RegisterListener> serverListeners = new ArrayList<>();
    private Scheduler serverScheduler;
    private Channel channel;

    public Server() {
        createConfig();
        if (!pluginData.exists()) pluginData.mkdir();
        this.port = Integer.parseInt(this.getConfigValue("server-port"));
        this.clientPassword = this.getConfigValue("synapse-password");
    }

    public void init() {
        ServerLogger.info("-- BeeCloud Proxy --");
        server = this;
        serverScheduler = new Scheduler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                TaskManager.prepare();
                serverScheduler.schedulerRunnableTask(new Runnable() {
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
                }, 1000);
                serverScheduler.schedulerRunnableTask(new Runnable() {
                    @Override
                    public void run() {
                        EventLoopGroup bossGroup = new NioEventLoopGroup();
                        EventLoopGroup workerGroup = new NioEventLoopGroup();
                        ServerBootstrap bootstrap = new ServerBootstrap();
                        bootstrap.group(bossGroup, workerGroup);
                        bootstrap.channel(NioServerSocketChannel.class);
                        bootstrap.childHandler(new NettyServerIniter());
                        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
                        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
                        try {
                            channel = bootstrap.bind(port).sync().channel();
                            ServerLogger.info("-- Running your proxy server on: " + port + " --");
                            channel.closeFuture().sync();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            bossGroup.shutdownGracefully();
                            workerGroup.shutdownGracefully();
                        }
                    }
                }, 2000);
                TaskManager.loop();
            }
        };
        CommandHandler.registerCommand(new HelpCommand());
        CommandHandler.registerCommand(new ListCommand());
        CommandHandler.registerCommand(new VersionCommand());
        CommandHandler.registerCommand(new PluginListCommand());
        CommandHandler.registerCommand(new StopCommand());
        EventHandler.setListener(new BeeCloudListener());
        Thread serverThread = new Thread(runnable);
        serverThread.setName("BeeCloud Main Thread");
        serverThread.start();
        ServerLogger.info("- Enabling plugins... -");
        PluginLoader.loadPlugin(this);
        Shutdown.shutdownTask();
        ServerLogger.info("- Done! For help type /help");

    }

    public void registerListeners(PluginBase plugin, Listener listener) {
        RegisterListener registerListener = new RegisterListener(plugin, listener);
    }

    public void reloadListener(Listener listener) {
        EventHandler.setListener(listener);
    }

    public String getName() {
        return "BeeCloud";
    }

    public void registerCommand(CommandHandler commandHandler) {
        CommandHandler.registerCommand(commandHandler);
    }

    public void registerNukkitCommand(CommandHandler commandHandler) {
        CommandHandler.registerCommand(commandHandler);
        CommandRegisterPacket pk = new CommandRegisterPacket(commandHandler.commandStr, commandHandler.commandUsage);
        CommandHandler.customCommandPacketList.add(pk);
        //send(pk);
    }

    public void sendPacket(BeeCloudPacket packet) {
        channel.writeAndFlush(packet);
    }

    public static Server getServer() {
        return server;
    }

    public Client getClient(String server) {
        return Client.getClient(server);
    }

    public Scheduler getServerScheduler() {
        return this.serverScheduler;
    }

    public int getOnlinePlayerCount() {
        return onLinePlayerList.size();
    }

    public ArrayList<SynapsePlayer> getOnLinePlayer() {
        return onLinePlayerList;
    }

    public boolean isPlayerOnline(SynapsePlayer player) {
        return onLinePlayerList.contains(player);
    }

    public boolean isPlayerOnLine(String player) {
        for (SynapsePlayer pl : onLinePlayerList)
            if (pl.player.equals(player))
                return true;
        return false;
    }

    private void createConfig() {
        if (!config.exists()) {
            writeData(config, "#Properties Config File");
            writeData(config, "server-port=8888");
            writeData(config, "server-ip=0.0.0.0");
            writeData(config, "synapse-password=123456789");
        }
    }

    public String getDataPath() {
        return this.serverPath;
    }

    public String getPluginData() {
        return this.pluginData.getPath();
    }

    public File getPluginFile() {
        return this.pluginData;
    }

    public void broadcastMessage(String message) {
        for (SynapsePlayer player : this.getOnLinePlayer())
            player.sendMessage(message);
        ServerLogger.info(message);
    }

    private String getConfigValue(String index) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.getDataPath() + "\\server.properties")));
            String lineData = null;
            while ((lineData = br.readLine()) != null) {
                String[] str1 = lineData.split("\\=");
                if (str1[0].equals(index)) return str1[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeData(File file, String data) {
        try {
            if (!file.exists()) file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            ;
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSavePlayerData(boolean savePlayerData) {
        SynapsePlayer.savePlayerData = savePlayerData;
    }

    private void titleTick() {
        Runtime runtime = Runtime.getRuntime();
        double used = BeeCloudMath.round((double) (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024, 2);
        double max = BeeCloudMath.round((double) runtime.maxMemory() / 1024.0D / 1024.0D, 2);
        String usage = Math.round(used / max * 100.0D) + "%";
        String title = this.getName() + " - Memory:" + used + "/" + max + "(" + usage + ")";
    }

}
