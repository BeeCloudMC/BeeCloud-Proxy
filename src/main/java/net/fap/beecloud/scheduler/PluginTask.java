package net.fap.beecloud.scheduler;

import net.fap.beecloud.plugin.PluginBase;

/**
 * 插件创建的任务
 *
 * @author catrainbow
 */

public class PluginTask extends Task {

    private PluginBase plugin;

    /**
     * 构造方法
     *
     * @param owner 任务的所有者
     */
    public PluginTask(PluginBase owner) {
        this.plugin = owner;
    }

    @Override
    public void run() {

    }

    /**
     * 返回任务所有者
     *
     * @return Plugin Owner
     */
    public PluginBase getPlugin() {
        return plugin;
    }

}
