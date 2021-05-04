package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.BeeCloud;

/**
 * 服务器数据更新数据包
 * 当收到服务器数据更新时发送这个数据包
 *
 * @author catrainbow
 */

public class ServerUpdatePacket extends BeeCloudPacket {

    public int count;

    @Override
    public void putString(String[] pk2) {
    }

    @Override
    public void resend() {
        this.count = BeeCloud.server.getOnlinePlayerCount();
    }

    @Override
    public String to_String() {
        return "ServerUpdatePacket:" + count;
    }
}
