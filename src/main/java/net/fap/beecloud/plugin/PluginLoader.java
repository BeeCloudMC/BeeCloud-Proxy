package net.fap.beecloud.plugin;

import net.fap.beecloud.Server;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.event.plugin.PluginEnableEvent;
import net.fap.beecloud.utils.XMLParser;

import java.util.List;

public class PluginLoader {

    public static void loadPlugin(Server server) {
        try {
            List<PluginBase> pluginList = XMLParser.getPluginList();
            PluginManager pluginManager = new PluginManager(pluginList);
            for (PluginBase plugin : pluginList) {
                if (PluginBase.pluginList.contains(plugin)) {
                    ServerLogger.info("无法加载插件 " + plugin.getName() + " 因为已经加载过了");
                } else {
                    PluginCase pluginLoader = pluginManager.getInstance(plugin.getClassName());
                    ServerLogger.waring("加载插件中: " + plugin.getName());
                    pluginLoader.onLoad();
                    ServerLogger.waring("开启插件中: " + plugin.getName());
                    ;
                    pluginLoader.onEnable();
                    PluginEnableEvent pluginEnableEvent = new PluginEnableEvent(plugin);
                    pluginEnableEvent.call();
                    ServerLogger.waring("插件 " + plugin.getName() + " 加载完成");
                    PluginBase.pluginList.add(plugin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
