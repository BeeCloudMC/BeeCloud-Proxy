package net.fap.beecloud.network.mcpe.protocol.custom;

import net.fap.beecloud.network.mcpe.protocol.BeeCloudPacket;

/**
 * 自定义数据包
 * 用于常规字符串交互
 *
 * @author catrainbow
 */

public class CustomPacket extends BeeCloudPacket {

    public String packetName;
    public String player;
    public String message;

    public String getPlayer() {
        return player;
    }

    @Override
    public void resend() {
    }

    @Override
    public void putString(String[] pk2) {
        this.packetName = pk2[0];
        this.player = pk2[1];
        this.message = pk2[2];
    }

    public String[] getPacketCode() {
        return message.split("\\:");
    }

    @Override
    public String to_String() {
        return packetName + ":" + player + ":" + message;
    }
}
